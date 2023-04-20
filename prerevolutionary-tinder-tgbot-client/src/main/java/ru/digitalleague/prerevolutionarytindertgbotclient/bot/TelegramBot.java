package ru.digitalleague.prerevolutionarytindertgbotclient.bot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.digitalleague.prerevolutionarytindertgbotclient.config.BotConfiguration;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class TelegramBot extends TelegramLongPollingBot {

    @Autowired
    private BotConfiguration botConfig;

    public void initBotCommands() {
        try {
            //TODO
            List<BotCommand> botCommands = new ArrayList<>();
            execute(new SetMyCommands(botCommands, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
            log.error("Error at create and setting bot`s command: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getBotUsername() {
        return botConfig.botName();
    }

    @Override
    public String getBotToken() {
        return botConfig.botToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        //TODO
    }
}
