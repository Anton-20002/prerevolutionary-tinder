package ru.digitalleague.prerevolutionarytindertgbotclient.bot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.digitalleague.prerevolutionarytindertgbotclient.bot.enums.BotCommandEnum;
import ru.digitalleague.prerevolutionarytindertgbotclient.bot.enums.ButtonCommandEnum;

@Slf4j
@Service
public class ParseCommandService {

    @Autowired
    private ButtonService buttonService;

    @Autowired
    private DbService dbService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private PictureService pictureService;

    public SendMessage parseCommand(String textCommand, long chatId) {
        log.info("Parse command");
        SendMessage sendMessage = new SendMessage();
        String command = textCommand.replaceAll("/", "");
        BotCommandEnum botCommandEnum = BotCommandEnum.valueOf(command.toUpperCase());

        switch (botCommandEnum) {
            case START: {
                sendMessage = buttonService.getButtonByCommand(botCommandEnum, chatId);
                break;
            }
            case HELP: {
                sendMessage.setText("""
                        Какая-то команда помощи
                        Потом разберусь чего сюда писать
                        """);
                break;
            }
        }
        return sendMessage;
    }

    public SendMessage parseButtonCommand(String data, long chatId) {
        log.info("Parse button command");
        SendMessage sendMessage = new SendMessage();
        ButtonCommandEnum buttonCommandEnum = ButtonCommandEnum.valueOf(data.toUpperCase().substring(1));

        if (buttonCommandEnum.equals(ButtonCommandEnum.SUDAR) || buttonCommandEnum.equals(ButtonCommandEnum.SUDARINYA)){
            dbService.saveMale(chatId, ButtonCommandEnum.SUDAR);
            sendMessage.setText(messageService.getMessage("bot.command.person.whatyourname"));
        } else if (buttonCommandEnum.equals(ButtonCommandEnum.SUDAR_SEARCH) || buttonCommandEnum.equals(ButtonCommandEnum.SUDARINYA_SEARCH) || buttonCommandEnum.equals(ButtonCommandEnum.ALL_PERSON_SEARCH)){
            dbService.saveSearchParam(chatId, buttonCommandEnum);
            //TODO обратиться в pictureService и сформировать картинку (заполненную анкету)
            sendMessage = buttonService.getMenuButtons();
        }

        return sendMessage;
    }

    public SendMessage parseInputText(String personName, long chatId) {

        if (!dbService.haveName(chatId)){
            return parsePersonName(personName, chatId);
        } else {
            return parseAboutPerson(personName, chatId);
        }
    }

    private SendMessage parsePersonName(String personName, long chatId){
        log.info("Parse person name command");
        dbService.savePersonName(personName, chatId);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(messageService.getMessage("bot.command.person.persondescription"));
        return sendMessage;
    }

    private SendMessage parseAboutPerson(String textCommand, long chatId) {
        log.info("Parse about person command");
        dbService.saveAboutPersonInformation(textCommand, chatId);
        pictureService.getAccountPicture(chatId);
        return buttonService.getButtonByCommand(ButtonCommandEnum.ABOUT, chatId);
    }
}
