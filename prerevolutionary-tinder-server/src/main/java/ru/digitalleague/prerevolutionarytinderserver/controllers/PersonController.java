package ru.digitalleague.prerevolutionarytinderserver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.digitalleague.prerevolutionarytinderdatabase.dtos.PersonDto;
import ru.digitalleague.prerevolutionarytinderdatabase.entities.Person;
import ru.digitalleague.prerevolutionarytinderdatabase.repositories.PersonRepository;
import ru.digitalleague.prerevolutionarytinderserver.servicies.PersonService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/person-controller")
public class PersonController {

    @Autowired
    PersonService personService;

    @GetMapping(value = "/is-registered-person-by-chat-id/{chatId}")
    public boolean isRegisteredPersonByChatId(@PathVariable("chatId") Long chatId) {
        return personService.isRegisteredPersonByChatId(chatId);
    }

    @GetMapping(value = "/get-dating-profiles-by-chat-id/{chatId}")
    public List<PersonDto> getDatingProfilesByChatId(@PathVariable("chatId") Long chatId) {
        return personService.getDatingProfilesByChatId(chatId);
    }
}
