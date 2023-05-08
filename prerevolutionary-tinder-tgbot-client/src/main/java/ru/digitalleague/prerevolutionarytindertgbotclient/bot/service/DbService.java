package ru.digitalleague.prerevolutionarytindertgbotclient.bot.service;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import ru.digitalleague.prerevolutionarytinderdatabase.enums.Gender;
import ru.digitalleague.prerevolutionarytinderdatabase.enums.Orientation;
import ru.digitalleague.prerevolutionarytindertgbotclient.bot.enums.ButtonCommandEnum;
import ru.digitalleague.prerevolutionarytindertgbotclient.bot.feign.FeignClientInterface;

import java.io.File;

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
        Gender gender;
        //сохраняем в БД пол по енуму и чатАйдишке
//        feignClientInterface.savePersonGender(chatId, gender.name());
    }

    public void savePersonName(String personName, long chatId) {
        //TODO
        //Сохраняем в БД имя по чатйдихе

//        feignClientInterface.savePersonName(chatId, personName);
    }

    public void saveAboutPersonInformation(String aboutText, long chatId) {
        //TODO
        //Сохраняем в БД инфу о персоне по чатйдихе
//        feignClientInterface.savePersonAboutInfo(chatId, aboutText);
    }

    public void saveSearchParam(long chatId, ButtonCommandEnum buttonCommandEnum) {
        //TODO
        Orientation orientation;
        //Сохраняем в БД параметр поиска (Енум параметр)
//        feignClientInterface.savePersonOrientation(chatId, orientation.name());
    }

    public boolean haveName(long chatId) {
        //TODO лезем в БД по чат айди, смотрим сохранено ли имя в Бд с этим чат айди.
//        return feignClientInterface.havePersonName(chatId);
        return true;
    }

    public SendPhoto getAccountPicture(long chatId) {
//        File file = feignClientInterface.getAccountPicture(chatId);
        //TODO
        /*
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setPhoto(new InputFile(file));
         */
        InputFile inputFile = new InputFile();
        inputFile.setMedia(getClass().getClassLoader().getResourceAsStream("picture.jpg"), "PHOTOCHKA");
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(chatId);
        sendPhoto.setPhoto(inputFile);
        return sendPhoto;
    }
}
