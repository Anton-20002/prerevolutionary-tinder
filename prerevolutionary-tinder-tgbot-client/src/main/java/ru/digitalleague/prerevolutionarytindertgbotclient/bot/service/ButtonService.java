package ru.digitalleague.prerevolutionarytindertgbotclient.bot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.digitalleague.prerevolutionarytindertgbotclient.bot.enums.BotCommandEnum;
import ru.digitalleague.prerevolutionarytindertgbotclient.bot.enums.ButtonCommandEnum;
import ru.digitalleague.prerevolutionarytindertgbotclient.bot.enums.ButtonMenuCommandEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ButtonService {

    @Autowired
    private DbService dbService;

    @Autowired
    private MessageService messageService;

    public SendMessage getButtonByCommand(BotCommandEnum commandEnum, long chatId){
        switch (commandEnum) {
            case START -> {
                if (!dbService.isRegistered(chatId)){
                    Map<String, String> paramMap = new HashMap<>();
                    paramMap.put(messageService.getMessage("bot.command.person.male.description"),messageService.getMessage("bot.command.person.male.name"));
                    paramMap.put(messageService.getMessage("bot.command.person.female.description"),messageService.getMessage("bot.command.person.female.name"));

                    SendMessage sendMessage = createKeyboardButtons(paramMap);
                    sendMessage.setText(messageService.getMessage("bot.command.person.whoareyou"));
                    sendMessage.setChatId(chatId);
                    return sendMessage;
                } else {
                    getMenuButtons(chatId);
                }

            }
        }
        return new SendMessage();
    }

    private SendMessage createKeyboardButtons(Map<String, String> paramMap){
        SendMessage sendMessage = new SendMessage();
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine = new ArrayList<>();

        for (Map.Entry<String, String> entry : paramMap.entrySet()){
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(entry.getValue());
            button.setCallbackData(entry.getKey());
            rowInLine.add(button);
        }

        rowsInLine.add(rowInLine);
        markup.setKeyboard(rowsInLine);
        sendMessage.setReplyMarkup(markup);

        return sendMessage;
    }

    public SendMessage getButtonByCommand(ButtonCommandEnum buttonCommandEnum, long chatId) {

        switch (buttonCommandEnum) {
            case ABOUT -> {
                Map<String, String> paramMap = new HashMap<>();
                paramMap.put(messageService.getMessage("bot.command.search.male.description"),messageService.getMessage("bot.command.search.male.name"));
                paramMap.put(messageService.getMessage("bot.command.search.female.description"),messageService.getMessage("bot.command.search.female.name"));
                paramMap.put(messageService.getMessage("bot.command.search.all_search.description"),messageService.getMessage("bot.command.search.all_search.name"));

                SendMessage sendMessage = createKeyboardButtons(paramMap);
                sendMessage.setText(messageService.getMessage("bot.command.search.whoareyou"));
                sendMessage.setChatId(chatId);
                return sendMessage;
            }
        }
        return new SendMessage();
    }

    public SendMessage getMenuButtons(long chatId) {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("//" + ButtonMenuCommandEnum.SEARCH.name(), ButtonMenuCommandEnum.SEARCH.getDescription());
        paramMap.put("//" + ButtonMenuCommandEnum.ACCOUNT.name(), ButtonMenuCommandEnum.ACCOUNT.getDescription());
        paramMap.put("//" + ButtonMenuCommandEnum.FAVORITES.name(), ButtonMenuCommandEnum.FAVORITES.getDescription());
        SendMessage sendMessage = createKeyboardButtons(paramMap);
        sendMessage.setText("Меню");
        sendMessage.setChatId(chatId);
        return sendMessage;
    }
}
