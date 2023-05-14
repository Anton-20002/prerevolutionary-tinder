package ru.digitalleague.prerevolutionarytindertgbotclient.bot.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;


@Data
@NoArgsConstructor
public class ImageMessageDto {
    private SendMessage sendMessage;
    private SendPhoto sendPhoto;
}