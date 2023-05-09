package ru.digitalleague.prerevolutionarytindertgbotclient.bot.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.digitalleague.prerevolutionarytindertgbotclient.bot.enums.ButtonCommandEnum;

import java.io.File;

@FeignClient(name = "feignClientInterface", url = "${feign.global.routes.url}")
public interface FeignClientInterface {

    @RequestMapping(method = RequestMethod.GET, value = "/is-registered-person-by-chat-id/{chatId}")
    boolean isRegistered(@RequestParam("chatId") Long chatId);

    @RequestMapping(method = RequestMethod.GET, value = "/get-account-picture/{chatId}")
    File getAccountPicture(@RequestParam("chatId") Long chatId);

    @RequestMapping(method = RequestMethod.GET, value = "/save-person-gender/{chatId}/{gender}")
    void savePersonGender(@RequestParam("chatId") Long chatId, @RequestParam("gender") String gender);

    @RequestMapping(method = RequestMethod.GET, value = "/save-person-name/{chatId}/{personName}")
    void savePersonName(@RequestParam("chatId") Long chatId, @RequestParam("personName") String personName);

    @RequestMapping(method = RequestMethod.GET, value = "/save-person-about-info/{chatId}/{aboutText}")
    void savePersonAboutInfo(@RequestParam("chatId") Long chatId , @RequestParam("aboutText") String aboutText);

    @RequestMapping(method = RequestMethod.GET, value = "/have-person-name/{chatId}/")
    boolean havePersonName(@RequestParam("chatId") Long chatId);

    @RequestMapping(method = RequestMethod.GET, value = "/save-person-orientation/{chatId}/{orientation}")
    void savePersonOrientation(@RequestParam("chatId") Long chatId, @RequestParam("orientation") String orientation);

    @RequestMapping(method = RequestMethod.GET, value = "/save-person-age/{chatId}")
    boolean haveAge(@RequestParam("chatId") Long chatId);

    @RequestMapping(method = RequestMethod.GET, value = "/save-person-header/{chatId}")
    boolean haveHeader(@RequestParam("chatId") Long chatId);

    @RequestMapping(method = RequestMethod.GET, value = "/save-person-age/{chatId}/{age}")
    void savePersonAge(@RequestParam("chatId") Long chatId, @RequestParam("age") Integer age);

    @RequestMapping(method = RequestMethod.GET, value = "/save-person-header/{chatId}/{header}")
    void savePersonHeader(@RequestParam("chatId") Long chatId, @RequestParam("header") String header);
}
