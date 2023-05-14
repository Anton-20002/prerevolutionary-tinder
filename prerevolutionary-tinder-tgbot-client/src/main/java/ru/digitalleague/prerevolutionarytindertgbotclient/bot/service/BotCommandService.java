package ru.digitalleague.prerevolutionarytindertgbotclient.bot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import ru.digitalleague.prerevolutionarytindertgbotclient.bot.enums.BotCommandEnum;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class BotCommandService {

    public List<BotCommand> getBotCommands() {
        log.info("Build dot commands");
        List<BotCommand> botCommands = new ArrayList<>();

        for (BotCommandEnum commandEnum : BotCommandEnum.values()) {
            String command = "/" + commandEnum.name().toLowerCase();
            botCommands.add(new BotCommand(command, commandEnum.getDescription()));
        }
        return botCommands;
    }
}
