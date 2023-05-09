package ru.digitalleague.prerevolutionarytinderserver;

import junit.framework.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.digitalleague.prerevolutionarytinderdatabase.dtos.PersonDto;
import ru.digitalleague.prerevolutionarytinderdatabase.entities.Person;
import ru.digitalleague.prerevolutionarytinderdatabase.enums.Gender;
import ru.digitalleague.prerevolutionarytinderdatabase.repositories.BlackListRepository;
import ru.digitalleague.prerevolutionarytinderdatabase.repositories.FavoriteListRepository;
import ru.digitalleague.prerevolutionarytinderdatabase.repositories.PersonRepository;
import ru.digitalleague.prerevolutionarytinderserver.servicies.ImageService;
import ru.digitalleague.prerevolutionarytinderserver.servicies.PersonService;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class PrerevolutionaryTinderServerApplicationTests {

	@Mock
	PersonRepository personRepository;
	@Mock
	BlackListRepository blackListRepository;
	@Mock
	FavoriteListRepository favoriteListRepository;
	@InjectMocks
	PersonService personService;

	@Test
	void isRegisteredPersonByChatId_shouldReturnFalse() {
		Long chatId = 00000L;
		Assert.assertFalse(personService.isRegisteredPersonByChatId(chatId));
	}

	@Test
	void getDatingProfilesByChatId_shouldReturnFalse() {
		Long chatId = 11111L;
		when(personRepository.findByChatId(chatId)).thenReturn(Optional.of(new Person()));
		List<PersonDto> datingProfilesByChatId = personService.getDatingProfilesByChatId(chatId);
		Assert.assertFalse(datingProfilesByChatId.isEmpty());
	}

	@Test
	void getDatingProfilesByChatId_shouldReturnTrue() {
		Long chatId = 00000L;
		when(personRepository.findByChatId(chatId)).thenReturn(Optional.empty());
		List<PersonDto> datingProfilesByChatId = personService.getDatingProfilesByChatId(chatId);
		Assert.assertTrue(datingProfilesByChatId.isEmpty());
	}

	@Test
	void getAccountPictureByChatId_shouldReturnFalse() {
		Long chatId = 11111L;
		Person person = new Person();
		person.setId(1L);
		when(personRepository.findByChatId(chatId)).thenReturn(Optional.of(person));
		List<Byte>  imageFile = personService.getAccountPicture(chatId);
		Assert.assertFalse(imageFile.isEmpty());
	}

	@Test
	void getAccountPictureByChatId_shouldReturnTrue() {
		Long chatId = 00000L;
		when(personRepository.findByChatId(chatId)).thenReturn(Optional.empty());
		List<Byte> imageFile = personService.getAccountPicture(chatId);
		Assert.assertTrue(imageFile.isEmpty());
	}

	@Test
	void savePersonGender_shouldReturnTrue() {
		Long chatId = 11111L;
		String gender = Gender.MALE.name();
		when(personRepository.findByChatId(chatId)).thenReturn(Optional.of(new Person()));
		when(personRepository.save(any())).thenReturn(new Person());
		Assert.assertTrue(personService.savePersonGender(chatId, gender));
	}

	@Test
	void savePersonGender_shouldReturnFalse() {
		Long chatId = 00000L;
		String gender = Gender.MALE.name();
		when(personRepository.findByChatId(chatId)).thenReturn(Optional.empty());
		Assert.assertFalse(personService.savePersonGender(chatId, gender));
	}

	@Test
	void savePersonName_shouldReturnTrue() {
		Long chatId = 11111L;
		String personName = "Белорус";
		when(personRepository.findByChatId(chatId)).thenReturn(Optional.of(new Person()));
		when(personRepository.save(any())).thenReturn(new Person());
		Assert.assertTrue(personService.savePersonName(chatId, personName));
	}

	@Test
	void savePersonName_shouldReturnFalse() {
		Long chatId = 00000L;
		String personName = "Белорус";
		when(personRepository.findByChatId(chatId)).thenReturn(Optional.empty());
		Assert.assertFalse(personService.savePersonName(chatId, personName));
	}

	@Test
	void savePersonAboutInfo_shouldReturnTrue() {
		Long chatId = 11111L;
		String aboutText = "Парень, 30 лет, скромный, трезвый, хорошего характера.";
		when(personRepository.findByChatId(chatId)).thenReturn(Optional.of(new Person()));
		when(personRepository.save(any())).thenReturn(new Person());
		Assert.assertTrue(personService.savePersonAboutInfo(chatId, aboutText));
	}

	@Test
	void savePersonAboutInfo_shouldReturnFalse() {
		Long chatId = 00000L;
		String aboutText = "Парень, 30 лет, скромный, трезвый, хорошего характера.";
		when(personRepository.findByChatId(chatId)).thenReturn(Optional.empty());
		Assert.assertFalse(personService.savePersonAboutInfo(chatId, aboutText));
	}

	@Test
	void havePersonName_shouldReturnTrue() {
		Long chatId = 11111L;
		Person person = new Person();
		person.setNickname("Никнейм");
		when(personRepository.findByChatId(chatId)).thenReturn(Optional.of(person));
		Assert.assertTrue(personService.havePersonName(chatId));
	}

	@Test
	void havePersonName_shouldReturnFalse() {
		Long chatId = 00000L;
		when(personRepository.findByChatId(chatId)).thenReturn(Optional.empty());
		Assert.assertFalse(personService.havePersonName(chatId));
	}

	@Test
	void savePersonOrientation_shouldReturnTrue() {
		Long chatId = 11111L;
		String orientation = "ALL";
		when(personRepository.findByChatId(chatId)).thenReturn(Optional.of(new Person()));
		when(personRepository.save(any())).thenReturn(new Person());
		Assert.assertTrue(personService.savePersonOrientation(chatId, orientation));
	}

	@Test
	void savePersonOrientation_shouldReturnFalse() {
		Long chatId = 00000L;
		String orientation = "ALL";
		when(personRepository.findByChatId(chatId)).thenReturn(Optional.empty());
		Assert.assertFalse(personService.savePersonOrientation(chatId, orientation));
	}
}
