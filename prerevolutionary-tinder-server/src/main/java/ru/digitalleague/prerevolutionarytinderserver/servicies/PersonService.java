package ru.digitalleague.prerevolutionarytinderserver.servicies;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.digitalleague.prerevolutionarytinderdatabase.dtos.PersonDto;
import ru.digitalleague.prerevolutionarytinderdatabase.entities.Person;
import ru.digitalleague.prerevolutionarytinderdatabase.repositories.BlackListRepository;
import ru.digitalleague.prerevolutionarytinderdatabase.repositories.FavoriteListRepository;
import ru.digitalleague.prerevolutionarytinderdatabase.repositories.PersonRepository;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        Optional<Person> personOptional = personRepository.findByChatId(chatId);
        return personOptional.isPresent();
    }

    public List<PersonDto> getDatingProfilesByChatId(Long chatId) {
        Optional<Person> personOptional = personRepository.findByChatId(chatId);
        Person mainPerson = personOptional.orElse(null);

        if (mainPerson == null) return new ArrayList<>();

        List<Person> favoriteDatingProfiles = personRepository.getFavoriteDatingProfilesByPersonId(mainPerson.getId());
        List<Person> anotherDatingProfiles = personRepository.getAnotherDatingProfilesByPersonId(mainPerson.getId(), mainPerson.getOrientation().getPartnersGenders(), mainPerson.getGender().getPartnersOrientations());
        favoriteDatingProfiles.addAll(anotherDatingProfiles);

        List<PersonDto> resultDatingProfiles = favoriteDatingProfiles.stream()
                .map(person -> {
                    return mapPersonOnPersonDto(person);
                })
                .collect(Collectors.toList());

        return resultDatingProfiles;
    }

    private PersonDto mapPersonOnPersonDto(Person person) {
        PersonDto personDto = new PersonDto();
        personDto.setId(person.getId());
        personDto.setNickname(person.getNickname());
        personDto.setGender(person.getGender());
        personDto.setOrientation(person.getOrientation());
        personDto.setAge(person.getAge());
        personDto.setHeader(person.getHeader());
        personDto.setDescription(person.getDescription());
        BufferedImage bufferedImage = imageService.createImage(person.getHeader(), person.getAge(), person.getDescription());
        personDto.setImage(bufferedImage);
        return personDto;
    }
}
