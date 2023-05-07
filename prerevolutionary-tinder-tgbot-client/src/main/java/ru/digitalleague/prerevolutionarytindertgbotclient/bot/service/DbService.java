package ru.digitalleague.prerevolutionarytindertgbotclient.bot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.digitalleague.prerevolutionarytindertgbotclient.bot.enums.ButtonCommandEnum;
import ru.digitalleague.prerevolutionarytindertgbotclient.bot.feign.FeignClientInterface;

import java.awt.*;

@Slf4j
@Service
public class DbService {

    @Autowired
    private FeignClientInterface feignClientInterface;

    public boolean isRegistered(long chatId) {
//        return feignClientInterface.isRegistered(chatId);
        return false;
    }


    public void saveMale(long chatId, ButtonCommandEnum buttonCommandEnum) {
        //TODO
        //сохраняем в БД пол по енуму и чатАйдишке
        feignClientInterface.savePersonMale(chatId, buttonCommandEnum);
    }

    public void savePersonName(String personName, long chatId) {
        //TODO
        //Сохраняем в БД имя по чатйдихе

        feignClientInterface.savePersonName(chatId, personName);
    }

    public void saveAboutPersonInformation(String aboutText, long chatId) {
        //TODO
        //Сохраняем в БД инфу о персоне по чатйдихе
        feignClientInterface.savePersonAboutInfo(chatId, aboutText);
    }

    public void saveSearchParam(long chatId, ButtonCommandEnum buttonCommandEnum) {
        //TODO
        //Сохраняем в БД параметр поиска (Енум параметр)
        feignClientInterface.savePersonSearchParam(chatId, buttonCommandEnum);
    }

    public boolean haveName(long chatId) {
        //TODO лезем в БД по чат айди, смотрим сохранено ли имя в Бд с этим чат айди.
//        return feignClientInterface.havePersonName(chatId);
        return false;
    }

    public Image getAccountPicture(long chatId) {
        return feignClientInterface.getAccountPicture(chatId);
    }
}
