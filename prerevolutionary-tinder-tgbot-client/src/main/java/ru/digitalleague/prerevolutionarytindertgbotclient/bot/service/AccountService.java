package ru.digitalleague.prerevolutionarytindertgbotclient.bot.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import ru.digitalleague.prerevolutionarytinderdatabase.dtos.PersonDto;
import ru.digitalleague.prerevolutionarytindertgbotclient.bot.entity.ImageMessageDto;
import ru.digitalleague.prerevolutionarytindertgbotclient.bot.enums.ButtonCommandEnum;

import java.io.File;

@Service
public class AccountService {

    private final DbService dbService;

    private final ButtonService buttonService;

    private final PictureService pictureService;

    private final MessageService messageService;

    public AccountService(DbService dbService, ButtonService buttonService,
                          PictureService pictureService, MessageService messageService) {
        this.dbService = dbService;
        this.buttonService = buttonService;
        this.pictureService = pictureService;
        this.messageService = messageService;
    }

    public ImageMessageDto searchAccount(long chatId) {
        PersonDto personDto = dbService.searchAccount(chatId);
        ImageMessageDto imageMessageDto = new ImageMessageDto();

        if (personDto.getIsEmpty()) {
            SendMessage sendMessage = buttonService.getMenuButtons(chatId);
            sendMessage.setChatId(chatId);
            sendMessage.setText(messageService.getMessage("bot.command.search.emptylist"));
            imageMessageDto.setSendMessage(sendMessage);
        } else {
            File picture = pictureService.createPicture(chatId, personDto.getImageFile());
            SendPhoto sendPhoto = pictureService.getSendPhoto(chatId, picture);
            SendMessage sendMessage = buttonService.getButtonByCommand(ButtonCommandEnum.SEARCH, chatId, personDto.getId());
            imageMessageDto.setSendPhoto(sendPhoto);
            imageMessageDto.setSendMessage(sendMessage);
        }
        return imageMessageDto;
    }
}