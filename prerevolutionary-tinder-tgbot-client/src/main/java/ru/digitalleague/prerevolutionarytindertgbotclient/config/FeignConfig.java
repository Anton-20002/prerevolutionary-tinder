package ru.digitalleague.prerevolutionarytindertgbotclient.config;


import feign.Feign;
import feign.Logger;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {
    @Bean
    public Feign.Builder feignBuilder() {
        return Feign.builder()
                .contract(new SpringMvcContract())
//                .encoder(new FeignJacksonEncoder(objectMapper()))
//                .decoder(new JacksonDecoder(objectMapper()))
//                .requestInterceptor(new FeignRequestInterceptor())
                .logger(new Logger.ErrorLogger())
                .logLevel(Logger.Level.BASIC);
    }
}