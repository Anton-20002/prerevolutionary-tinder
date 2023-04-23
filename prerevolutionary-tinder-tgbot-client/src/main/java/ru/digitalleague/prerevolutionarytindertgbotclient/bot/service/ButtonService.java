package ru.digitalleague.prerevolutionarytindertgbotclient.bot.service;

import jakarta.validation.constraints.NotNull;
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

    public SendMessage getButtonByCommand(BotCommandEnum commandEnum, long chatId){
        switch (commandEnum) {
            case START -> {
                if (!dbService.isRegistered(chatId)){

                    Map<String, String> paramMap = new HashMap<>();
                    paramMap.put("sudar", "Сударь");
                    paramMap.put("sudarinya", "Сударыня");

                    SendMessage sendMessage = createKeyboardButtons(paramMap);
                    sendMessage.setText("Вы сударь иль сударыня?");
                    return sendMessage;
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
                paramMap.put("sudar_search", "Сударь");
                paramMap.put("sudarinya_search", "Сударыня");
                paramMap.put("all_person_search", "Всех");

                SendMessage sendMessage = createKeyboardButtons(paramMap);
                sendMessage.setText("Кого вы ищите?");
                return sendMessage;
            }
        }
        return new SendMessage();
    }

    public SendMessage getMenuButtons() {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("//" + ButtonMenuCommandEnum.SEARCH.name(), ButtonMenuCommandEnum.SEARCH.getDescription());
        paramMap.put("//" + ButtonMenuCommandEnum.ACCOUNT.name(), ButtonMenuCommandEnum.ACCOUNT.getDescription());
        paramMap.put("//" + ButtonMenuCommandEnum.FAVORITES.name(), ButtonMenuCommandEnum.FAVORITES.getDescription());
        SendMessage sendMessage = createKeyboardButtons(paramMap);
        sendMessage.setText("Меню");
        return sendMessage;
    }
}
