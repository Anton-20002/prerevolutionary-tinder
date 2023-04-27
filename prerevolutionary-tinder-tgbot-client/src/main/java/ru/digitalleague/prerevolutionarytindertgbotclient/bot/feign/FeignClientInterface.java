package ru.digitalleague.prerevolutionarytindertgbotclient.bot.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "feignClientInterface", url = "http://localhost:8888/person-controller")
public interface FeignClientInterface {

    @RequestMapping(method = RequestMethod.GET, value = "/is-registered-person-by-chat-id/{chatId}")
    boolean isValid(@RequestParam Long chatId);
}
