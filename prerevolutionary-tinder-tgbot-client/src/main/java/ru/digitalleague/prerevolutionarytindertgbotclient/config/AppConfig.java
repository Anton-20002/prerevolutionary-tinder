package ru.digitalleague.prerevolutionarytindertgbotclient.config;


import feign.Feign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import ru.digitalleague.prerevolutionarytindertgbotclient.bot.feign.FeignClientInterface;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages={"ru.digitalleague.prerevolutionarytindertgbotclient", "ru.digitalleague.prerevolutionarytindertgbotclient.bot.feign"})
public class AppConfig {
    @Autowired
    private Environment env;

    @Bean
    public FeignClientInterface feignClientInterface(@Autowired Feign.Builder feignBuilder) {
        return feignBuilder.target(FeignClientInterface.class, env.getProperty("feign.global.routes.url"));
    }

    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        messageSource.setDefaultEncoding("UTF-8");
        ResourceBundleMessageSource commonMessageSource = new ResourceBundleMessageSource();
        commonMessageSource.setBasename("common_messages");
        commonMessageSource.setDefaultEncoding("UTF-8");
        messageSource.setParentMessageSource(commonMessageSource);
        return messageSource;
    }
}