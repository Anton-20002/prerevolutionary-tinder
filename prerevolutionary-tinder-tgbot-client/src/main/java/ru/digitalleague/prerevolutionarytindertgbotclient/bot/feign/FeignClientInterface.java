package ru.digitalleague.prerevolutionarytindertgbotclient.bot.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.digitalleague.prerevolutionarytinderdatabase.dtos.FavoritePersonDto;
import ru.digitalleague.prerevolutionarytinderdatabase.dtos.PersonDto;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "feignClientInterface", url = "${feign.global.routes.url}")
public interface FeignClientInterface {

    @RequestMapping(method = RequestMethod.GET, value = "/is-registered-person-by-chat-id/{chatId}")
    boolean isRegistered(@RequestParam("chatId") Long chatId);

    @RequestMapping(method = RequestMethod.GET, value = "/get-account-picture/{chatId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    List<Byte> getAccountPicture(@RequestParam("chatId") Long chatId);

    @RequestMapping(method = RequestMethod.GET, value = "/save-person-gender/{chatId}/{gender}")
    void savePersonGender(@RequestParam("chatId") Long chatId, @RequestParam("gender") String gender);

    @RequestMapping(method = RequestMethod.GET, value = "/save-person-name/{chatId}/{personName}")
    void savePersonName(@RequestParam("chatId") Long chatId, @RequestParam("personName") String personName);

    @RequestMapping(method = RequestMethod.GET, value = "/save-person-about-info/{chatId}/{aboutText}")
    void savePersonAboutInfo(@RequestParam("chatId") Long chatId, @RequestParam("aboutText") String aboutText);

    @RequestMapping(method = RequestMethod.GET, value = "/have-person-name/{chatId}/")
    boolean havePersonName(@RequestParam("chatId") Long chatId);

    @RequestMapping(method = RequestMethod.GET, value = "/save-person-orientation/{chatId}/{orientation}")
    void savePersonOrientation(@RequestParam("chatId") Long chatId, @RequestParam("orientation") String orientation);

    @RequestMapping(method = RequestMethod.GET, value = "/have-person-age/{chatId}")
    boolean haveAge(@RequestParam("chatId") Long chatId);

    @RequestMapping(method = RequestMethod.GET, value = "/have-person-header/{chatId}")
    boolean haveHeader(@RequestParam("chatId") Long chatId);

    @RequestMapping(method = RequestMethod.GET, value = "/save-person-age/{chatId}/{age}")
    void savePersonAge(@RequestParam("chatId") Long chatId, @RequestParam("age") Integer age);

    @RequestMapping(method = RequestMethod.GET, value = "/save-person-header/{chatId}/{header}")
    void savePersonHeader(@RequestParam("chatId") Long chatId, @RequestParam("header") String header);

    @RequestMapping(method = RequestMethod.GET, value = "/get-dating-profiles-by-chat-id/{chatId}")
    PersonDto searchAccount(@RequestParam("chatId") Long chatId);

    @RequestMapping(method = RequestMethod.GET, value = "/add-person-to-blacklist/{chatId}/{bannedPersonId}")
    void addPersonToBlacklist(@RequestParam("chatId") Long chatId, @RequestParam("bannedPersonId") Long bannedPersonId);

    @RequestMapping(method = RequestMethod.GET, value = "/add-person-to-favotitelist/{chatId}/{favoritePersonId}")
    void addPersonToFavoritelist(@RequestParam("chatId") Long chatId, @RequestParam("favoritePersonId") Long favoritePersonId);

    @RequestMapping(method = RequestMethod.GET, value = "/get-favorites-profiles-by-chat-id/{chatId}")
    List<FavoritePersonDto> getFavoritesProfilesByChatId(@PathVariable("chatId") Long chatId);
}
