package ru.digitalleague.prerevolutionarytindertgbotclient.bot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@Slf4j
public class MessageService {
    private final Locale russian = new Locale("ru", "RU"); //глянуть саму локаль как передавать


    @Autowired
    private ResourceBundleMessageSource messageSource;

    public String getMessage(String code) {
        try {
            return messageSource.getMessage(code, null, russian);
        } catch (Exception e) {
            log.warn("Get message key='" + code + "' error:" + e.getMessage());
        }
        return code;
    }
}
