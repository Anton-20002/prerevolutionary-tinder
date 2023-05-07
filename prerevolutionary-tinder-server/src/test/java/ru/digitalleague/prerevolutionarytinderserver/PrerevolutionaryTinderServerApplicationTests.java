package ru.digitalleague.prerevolutionarytinderserver;

import junit.framework.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.digitalleague.prerevolutionarytinderdatabase.dtos.PersonDto;
import ru.digitalleague.prerevolutionarytinderserver.servicies.PersonService;

import java.util.List;

@SpringBootTest
class PrerevolutionaryTinderServerApplicationTests {

	@Autowired
	PersonService personService;

	@Test
	void isRegisteredPersonByChatId_shouldReturnTrue() {
		Assert.assertTrue(personService.isRegisteredPersonByChatId(11111L));
	}

	@Test
	void isRegisteredPersonByChatId_shouldReturnFalse() {
		Assert.assertFalse(personService.isRegisteredPersonByChatId(00000L));
	}

	@Test
	void getDatingProfilesByChatId_shouldReturnFalse() {
		List<PersonDto> datingProfilesByChatId = personService.getDatingProfilesByChatId(11111L);
		Assert.assertFalse(datingProfilesByChatId.isEmpty());
	}

	@Test
	void getDatingProfilesByChatId_shouldReturnTrue() {
		List<PersonDto> datingProfilesByChatId = personService.getDatingProfilesByChatId(00000L);
		Assert.assertTrue(datingProfilesByChatId.isEmpty());
	}

}
