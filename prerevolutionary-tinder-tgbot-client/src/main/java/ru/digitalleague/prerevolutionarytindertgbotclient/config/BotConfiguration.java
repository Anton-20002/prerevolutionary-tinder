package ru.digitalleague.prerevolutionarytindertgbotclient.config;

import lombok.Getter;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Getter
@Accessors(fluent = true)
@Configuration
@PropertySource("application.properties")
public class BotConfiguration {

    @Value("${bot.name}")
    private String botName;

    @Value("${bot.token}")
    private String botToken;
}
