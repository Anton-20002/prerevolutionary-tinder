package ru.digitalleague.prerevolutionarytinderserver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.digitalleague.prerevolutionarytinderdatabase.entities.Person;
import ru.digitalleague.prerevolutionarytinderdatabase.repositories.PersonRepository;

import java.util.Optional;

@Controller
public class ServerController {

    @Autowired
    PersonRepository personRepository;

    private boolean isRegisteredPersonByChatId(long chatId) {
        Optional<Person> personOptional = personRepository.findByChatId(chatId);
        return personOptional.isPresent();
    }
}
