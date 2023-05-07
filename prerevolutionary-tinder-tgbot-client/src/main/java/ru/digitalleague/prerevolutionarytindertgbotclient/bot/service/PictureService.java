package ru.digitalleague.prerevolutionarytindertgbotclient.bot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;

@Slf4j
@Service
public class PictureService {

    @Autowired
    private DbService dbService;
    public SendPhoto getAccountPicture(long chatId) {
        return dbService.getAccountPicture(chatId);
    }
}
