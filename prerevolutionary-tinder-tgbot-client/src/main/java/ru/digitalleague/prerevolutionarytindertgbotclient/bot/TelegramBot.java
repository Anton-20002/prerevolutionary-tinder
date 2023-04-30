package ru.digitalleague.prerevolutionarytindertgbotclient.bot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.digitalleague.prerevolutionarytindertgbotclient.bot.service.BotCommandService;
import ru.digitalleague.prerevolutionarytindertgbotclient.bot.service.ParseCommandService;
import ru.digitalleague.prerevolutionarytindertgbotclient.config.BotConfiguration;

import java.util.List;

@Slf4j
@Component
public class TelegramBot extends TelegramLongPollingBot {

    @Autowired
    private BotConfiguration botConfig;

    @Autowired
    private BotCommandService botCommandService;

    @Autowired
    private ParseCommandService parseCommandService;

    public void initBotCommands() {
        try {
            //TODO
            List<BotCommand> botCommands = botCommandService.getBotCommands();
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
        SendMessage sendMessage = new SendMessage();
        SendPhoto sendPhoto = null;
        /**
        
        update.getMessage() == null прерывать выполнение кода, выводить информацию в телеге о необходимости заполнение текстом
        
        
        
        */
        if (update.getMessage() != null && update.getMessage().getText().contains("/")) {
            sendMessage = parseCommandService.parseCommand(update.getMessage().getText(), update.getMessage().getChatId());
            sendMessage.setChatId(update.getMessage().getChatId());
            
            /**
            
            update.getCallbackQuery().getMessage().getChatId() null pointer exception
            
            
            */
        } else if (update.hasCallbackQuery()) {
            sendMessage = parseCommandService.parseButtonCommand(update.getCallbackQuery().getData(), update.getCallbackQuery().getMessage().getChatId());
            sendMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());
            
            /**
            
            update.getMessage().getText().contains("\n") непонятная логика contains("\n")
            
            update.getMessage().getText().contains("\n") null pointer exception
            
            */
        } else if (update.getMessage() != null && !update.getMessage().getText().contains("\n")){
            sendMessage = parseCommandService.parsePersonName(update.getMessage().getText(), update.getMessage().getChatId());
            sendMessage.setChatId(update.getMessage().getChatId());
               /**
               
               много if else лучше в отдельный метод
               
               */
        } else if (update.getMessage() != null && update.getMessage().getText().contains("\n")){
            sendMessage = parseCommandService.parseAboutPerson(update.getMessage().getText(), update.getMessage().getChatId());
            sendMessage.setChatId(update.getMessage().getChatId());
        }

        try {
            log.info("execute message");
            execute(sendMessage);
            log.info("execute message complete");
            if (sendPhoto != null) {
                sendPhoto.setChatId(update.getMessage().getChatId());
                log.info("execute photo");
                execute(sendPhoto);
                log.info("execute photo complete");
            }
        } catch (TelegramApiException e) {
            log.error("Error sending message or photo: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
