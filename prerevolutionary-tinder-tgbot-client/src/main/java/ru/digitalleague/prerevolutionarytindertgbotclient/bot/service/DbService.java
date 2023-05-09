package ru.digitalleague.prerevolutionarytindertgbotclient.bot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.digitalleague.prerevolutionarytindertgbotclient.bot.enums.ButtonCommandEnum;
import ru.digitalleague.prerevolutionarytindertgbotclient.bot.feign.FeignClientInterface;

import java.util.List;

@Slf4j
@Service
public class DbService {

    @Autowired
    private FeignClientInterface feignClientInterface;

    public boolean isRegistered(long chatId) {
        return feignClientInterface.isRegistered(chatId);
    }

    public void savePersonGender(long chatId, ButtonCommandEnum buttonCommandEnum) {
        feignClientInterface.savePersonGender(chatId, buttonCommandEnum.name());
    }

    public void savePersonName(String personName, long chatId) {
        feignClientInterface.savePersonName(chatId, personName);
    }

    public void saveAboutPersonInformation(String aboutText, long chatId) {
        feignClientInterface.savePersonAboutInfo(chatId, aboutText);
    }

    public void savePersonOrientation(long chatId, ButtonCommandEnum buttonCommandEnum) {
        feignClientInterface.savePersonOrientation(chatId, buttonCommandEnum.name());
    }

    public boolean haveName(long chatId) {
        return feignClientInterface.havePersonName(chatId);
    }

    public List<Byte> getAccountPicture(long chatId) {
        return feignClientInterface.getAccountPicture(chatId);
    }

    public boolean haveAge(long chatId) {
        return feignClientInterface.haveAge(chatId);
    }

    public boolean haveHeader(long chatId) {
        return feignClientInterface.haveHeader(chatId);
    }

    public void savePersonAge(int textCommand, long chatId) {
        feignClientInterface.savePersonAge(chatId, textCommand);
    }

    public void savePersonHeader(String textCommand, long chatId) {
        feignClientInterface.savePersonHeader(chatId, textCommand);
    }
}
