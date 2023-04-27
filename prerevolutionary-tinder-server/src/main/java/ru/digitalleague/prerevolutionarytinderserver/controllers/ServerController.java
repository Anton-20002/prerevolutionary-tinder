package ru.digitalleague.prerevolutionarytinderserver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.digitalleague.prerevolutionarytinderdatabase.entities.Person;
import ru.digitalleague.prerevolutionarytinderdatabase.repositories.PersonRepository;

import java.util.Optional;

@RestController
@RequestMapping(value = "/person-attachment")
public class ServerController {

    @Autowired
    PersonRepository personRepository;

    @GetMapping(value = "/is-registered-person-by-chat-id/{chatId}")
    public boolean isRegisteredPersonByChatId(@PathVariable("chatId") Long chatId) {
        Optional<Person> personOptional = personRepository.findByChatId(chatId);
        return personOptional.isPresent();
    }
}
