package ru.digitalleague.prerevolutionarytinderserver.configs;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages={"ru.digitalleague.prerevolutionarytinderdatabase",
                             "ru.digitalleague.prerevolutionarytinderserver"})
public class AppConfig {
}
