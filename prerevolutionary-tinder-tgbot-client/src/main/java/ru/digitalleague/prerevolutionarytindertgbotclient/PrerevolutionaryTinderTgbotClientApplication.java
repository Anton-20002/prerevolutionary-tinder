package ru.digitalleague.prerevolutionarytindertgbotclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class PrerevolutionaryTinderTgbotClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(PrerevolutionaryTinderTgbotClientApplication.class, args);
    }

}
