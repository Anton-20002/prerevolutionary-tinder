package ru.digitalleague.prerevolutionarytindertgbotclient.bot.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "feignClientInterface", url = "${feign.global.routes.url}")
public interface FeignClientInterface {

    @RequestMapping(method = RequestMethod.GET, value = "/person-controller/is-registered-person-by-chat-id/{chatId}")
    boolean isValid(@RequestParam("chatId") Long chatId);
}
