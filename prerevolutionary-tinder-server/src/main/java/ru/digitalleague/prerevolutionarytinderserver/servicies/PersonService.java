package ru.digitalleague.prerevolutionarytinderserver.servicies;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.digitalleague.prerevolutionarytinderdatabase.dtos.FavoritePersonDto;
import ru.digitalleague.prerevolutionarytinderdatabase.dtos.PersonDto;
import ru.digitalleague.prerevolutionarytinderdatabase.entities.BlackList;
import ru.digitalleague.prerevolutionarytinderdatabase.entities.FavoriteList;
import ru.digitalleague.prerevolutionarytinderdatabase.entities.Person;
import ru.digitalleague.prerevolutionarytinderdatabase.enums.Gender;
import ru.digitalleague.prerevolutionarytinderdatabase.enums.Orientation;
import ru.digitalleague.prerevolutionarytinderdatabase.enums.RomanceStatus;
import ru.digitalleague.prerevolutionarytinderdatabase.repositories.BlackListRepository;
import ru.digitalleague.prerevolutionarytinderdatabase.repositories.FavoriteListRepository;
import ru.digitalleague.prerevolutionarytinderdatabase.repositories.PersonRepository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PersonService {

    PersonRepository personRepository;
    BlackListRepository blackListRepository;
    FavoriteListRepository favoriteListRepository;
    ImageService imageService;

    @Autowired
    public PersonService(PersonRepository personRepository, BlackListRepository blackListRepository, FavoriteListRepository favoriteListRepository, ImageService imageService) {
        this.personRepository = personRepository;
        this.blackListRepository = blackListRepository;
        this.favoriteListRepository = favoriteListRepository;
        this.imageService = imageService;
    }

    public boolean isRegisteredPersonByChatId(Long chatId) {
        log.info("Check is registered person by chatId {}", chatId);
        Optional<Person> personOptional = personRepository.findByChatId(chatId);
        return personOptional.isPresent();
    }

    public List<PersonDto> getDatingProfilesByChatId(Long chatId) {
        log.info("Get dating profiles by chatId {}", chatId);
        Optional<Person> personOptional = personRepository.findByChatId(chatId);
        Person mainPerson = personOptional.orElse(null);

        if (mainPerson == null) return new ArrayList<>();

        List<Person> favoriteDatingProfiles = personRepository.getFavoriteDatingProfilesByPersonId(mainPerson.getId());
        List<Person> anotherDatingProfiles = personRepository.getAnotherDatingProfilesByPersonId(mainPerson.getId(), mainPerson.getOrientation().getPartnersGenders(), mainPerson.getGender().getPartnersOrientations());
        favoriteDatingProfiles.addAll(anotherDatingProfiles);

        List<PersonDto> resultDatingProfiles = favoriteDatingProfiles.stream()
                .map(person -> mapPersonOnPersonDto(person))
                .collect(Collectors.toList());

        return resultDatingProfiles;
    }

    private PersonDto mapPersonOnPersonDto(Person person) {
        log.debug("Mapping person on personDto personId {}", person.getId());
        PersonDto personDto = new PersonDto();
        personDto.setId(person.getId());
        personDto.setNickname(person.getNickname());
        personDto.setGender(person.getGender());
        personDto.setOrientation(person.getOrientation());
        personDto.setAge(person.getAge());
        personDto.setHeader(person.getHeader());
        personDto.setDescription(person.getDescription());
        File file = imageService.createImage(person.getId(), person.getHeader(), person.getAge(), person.getDescription());
        personDto.setImageFile(file);
        return personDto;
    }

    public List<FavoritePersonDto> getFavoritesProfilesByChatId(Long chatId) {
        log.info("Get favorites profiles by chatId {}", chatId);
        Optional<Person> personOptional = personRepository.findByChatId(chatId);
        Person mainPerson = personOptional.orElse(null);

        if (mainPerson == null) return new ArrayList<>();

        List<Person> favoriteDatingProfiles = personRepository.getDistinctFavoriteDatingProfilesByPersonId(mainPerson.getId());

        List<FavoritePersonDto> favoritePersonDtos = favoriteDatingProfiles.stream()
                .map(person -> mapPersonOnFavoritePersonDto(mainPerson.getId(), person))
                .collect(Collectors.toList());

        return favoritePersonDtos;
    }

    private FavoritePersonDto mapPersonOnFavoritePersonDto(Long mainPersonId, Person person) {
        log.debug("Mapping person on favoritePersonDto personId {}", person.getId());
        FavoritePersonDto favoritePersonDto = new FavoritePersonDto();
        favoritePersonDto.setId(person.getId());
        favoritePersonDto.setNickname(person.getNickname());
        favoritePersonDto.setGender(person.getGender());
        favoritePersonDto.setOrientation(person.getOrientation());
        favoritePersonDto.setAge(person.getAge());
        favoritePersonDto.setHeader(person.getHeader());
        favoritePersonDto.setDescription(person.getDescription());
        File file = imageService.createImage(person.getId(), person.getHeader(), person.getAge(), person.getDescription());
        favoritePersonDto.setImageFile(file);
        favoritePersonDto.setRomanceStatus(getRomanceStatus(mainPersonId, person.getId()));
        return favoritePersonDto;
    }

    private RomanceStatus getRomanceStatus(Long mainPersonId, Long personId) {
        log.debug("Get romance status by mainPersonId {} and personId {}", mainPersonId, personId);
        Optional<FavoriteList> mainPersonIdAndPersonIdFavoriteList = favoriteListRepository.findByPersonIdAndFavoritePersonId(mainPersonId, personId);
        FavoriteList favoriteList = mainPersonIdAndPersonIdFavoriteList.orElse(null);

        if (favoriteList != null) {
            return favoriteList.getRomanceStatus();
        } else {
            Optional<FavoriteList> personIdAndMainPersonIdFavoriteList = favoriteListRepository.findByPersonIdAndFavoritePersonId(personId, mainPersonId);
            favoriteList = personIdAndMainPersonIdFavoriteList.orElse(null);

            if (favoriteList != null) {
                return RomanceStatus.YOU_LIKED;
            } else {
                throw new RuntimeException("Cant find at FavoriteList romance status!");
            }
        }
    }

    public File getAccountPicture(Long chatId) {
        log.info("Get account picture chatId {}", chatId);
        Optional<Person> personOptional = personRepository.findByChatId(chatId);
        Person person = personOptional.orElse(null);

        if (person == null) return new File("empty.png");

        return imageService.createImage(person.getId(), person.getHeader(), person.getAge(), person.getDescription());
    }

    public boolean savePersonGender(Long chatId, String gender) {
        log.info("Save person by chatId {} with gender {}", chatId, gender);
        Person person = new Person();
        person.setGender(Gender.valueOf(gender));
        personRepository.save(person);
        return true;
    }

    public boolean savePersonName(Long chatId, String personName) {
        log.info("Save person by chatId {} with name {}", chatId, personName);
        Optional<Person> personOptional = personRepository.findByChatId(chatId);
        Person person = personOptional.orElse(null);

        if (person == null) return false;

        person.setNickname(personName);
        personRepository.save(person);
        return true;
    }

    public boolean savePersonAboutInfo(Long chatId, String aboutText) {
        log.info("Save person by chatId {} with aboutText {}", chatId, aboutText);
        Optional<Person> personOptional = personRepository.findByChatId(chatId);
        Person person = personOptional.orElse(null);

        if (person == null) return false;

        person.setDescription(aboutText);
        personRepository.save(person);
        return true;
    }

    public boolean havePersonName(Long chatId) {
        Optional<Person> personOptional = personRepository.findByChatId(chatId);
        Person person = personOptional.orElse(null);

        if (person == null) {
            return false;
        }
        if (person.getNickname() == null || person.getNickname().isEmpty()) {
            return false;
        }
        return true;
    }

    public boolean savePersonOrientation(Long chatId, String orientation) {
        log.info("Save person by chatId {} with orientation {}", chatId, orientation);
        Optional<Person> personOptional = personRepository.findByChatId(chatId);
        Person person = personOptional.orElse(null);

        if (person == null) return false;

        person.setOrientation(Orientation.valueOf(orientation));
        personRepository.save(person);
        return true;
    }

    public boolean addPersonToBlacklist(Long chatId, Long bannedPersonId) {
        log.info("At chat {} ban person with id {}", chatId, bannedPersonId);
        Optional<Person> personOptional = personRepository.findByChatId(chatId);
        Person person = personOptional.orElse(null);

        if (person == null) return false;

        BlackList blackList = new BlackList();
        blackList.setPersonId(person.getId());
        blackList.setBannedPersonId(bannedPersonId);

        blackListRepository.save(blackList);
        return true;
    }

    public boolean addPersonToFavoritelist(Long chatId, Long favoritePersonId) {
        log.info("At chat {} add to favorite person with id {}", chatId, favoritePersonId);
        Optional<Person> personOptional = personRepository.findByChatId(chatId);
        Person person = personOptional.orElse(null);

        if (person == null) return false;

        FavoriteList favoriteList = new FavoriteList();
        favoriteList.setPersonId(person.getId());
        favoriteList.setFavoritePersonId(favoritePersonId);
        favoriteList.setRomanceStatus(getRomanceStatusAndUpdate(person.getId(), favoritePersonId));

        favoriteListRepository.save(favoriteList);
        return true;


    }

    private RomanceStatus getRomanceStatusAndUpdate(Long personId, Long favoritePersonId) {
        log.debug("Get romance status by mainPersonId {} and favoritePersonId {}", personId, favoritePersonId);
        Optional<FavoriteList> favoriteListOptional = favoriteListRepository.findByPersonIdAndFavoritePersonId(favoritePersonId, personId);
        FavoriteList favoriteList = favoriteListOptional.orElse(null);
        if (favoriteList == null) {
            return RomanceStatus.YOU_LIKE;
        } else {
            favoriteList.setRomanceStatus(RomanceStatus.MUTUALLY_LIKED);
            favoriteListRepository.save(favoriteList);
            return RomanceStatus.MUTUALLY_LIKED;
        }
    }
}
