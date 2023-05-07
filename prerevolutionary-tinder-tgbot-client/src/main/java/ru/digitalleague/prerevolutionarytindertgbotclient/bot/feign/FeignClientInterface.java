package ru.digitalleague.prerevolutionarytindertgbotclient.bot.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.digitalleague.prerevolutionarytindertgbotclient.bot.enums.ButtonCommandEnum;

import java.awt.*;

@FeignClient(name = "feignClientInterface", url = "${feign.global.routes.url}")
public interface FeignClientInterface {

    @RequestMapping(method = RequestMethod.GET, value = "/is-registered-person-by-chat-id/{chatId}")
    boolean isRegistered(@RequestParam("chatId") Long chatId);

    @RequestMapping(method = RequestMethod.GET, value = "/get-account-picture/{chatId}")
    Image getAccountPicture(@RequestParam("chatId") Long chatId);

    @RequestMapping(method = RequestMethod.GET, value = "/save-person-male/{chatId}/{buttonCommandEnum}")
    void savePersonMale(@RequestParam("chatId") Long chatId, @RequestParam("buttonCommandEnum") ButtonCommandEnum buttonCommandEnum);

    @RequestMapping(method = RequestMethod.GET, value = "/save-person-name/{chatId}/{personName}")
    void savePersonName(@RequestParam("chatId") Long chatId, @RequestParam("personName") String personName);

    @RequestMapping(method = RequestMethod.GET, value = "/save-person-about-info{chatId}/{aboutText}")
    void savePersonAboutInfo(@RequestParam("chatId") Long chatId , @RequestParam("aboutText") String aboutText);

    @RequestMapping(method = RequestMethod.GET, value = "/have-person-name{chatId}/")
    boolean havePersonName(@RequestParam("chatId") Long chatId);

    @RequestMapping(method = RequestMethod.GET, value = "/save-person-search-param/{chatId}/{buttonCommandEnum}")
    void savePersonSearchParam(@RequestParam("chatId") Long chatId, @RequestParam("buttonCommandEnum") ButtonCommandEnum buttonCommandEnum);
}
