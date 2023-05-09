package ru.digitalleague.prerevolutionarytindertgbotclient.bot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.digitalleague.prerevolutionarytindertgbotclient.bot.entity.ImageMessageDto;
import ru.digitalleague.prerevolutionarytindertgbotclient.bot.enums.BotCommandEnum;
import ru.digitalleague.prerevolutionarytindertgbotclient.bot.enums.ButtonCommandEnum;

@Slf4j
@Service
public class ParseCommandService {

    @Autowired
    private ButtonService buttonService;

    @Autowired
    private DbService dbService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private PictureService pictureService;

    public SendMessage parseCommand(String textCommand, long chatId) {
        log.info("Parse command");
        SendMessage sendMessage = new SendMessage();
        String command = textCommand.replaceAll("/", "");
        BotCommandEnum botAndButtonCommandEnum = BotCommandEnum.valueOf(command.toUpperCase());

        switch (botAndButtonCommandEnum) {
            case START: {
                sendMessage = buttonService.getButtonByCommand(botAndButtonCommandEnum, chatId);
                break;
            }
            case HELP: {
                sendMessage.setText("""
                        Какая-то команда помощи
                        Потом разберусь чего сюда писать
                        """);
                break;
            }
        }
        return sendMessage;
    }

    public ImageMessageDto parseButtonCommand(String data, long chatId) {
        log.info("Parse button command");
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        ButtonCommandEnum buttonCommandEnum = ButtonCommandEnum.valueOf(data.toUpperCase().substring(1));
        ImageMessageDto imageMessageDto = new ImageMessageDto();

        if (buttonCommandEnum.equals(ButtonCommandEnum.MALE) || buttonCommandEnum.equals(ButtonCommandEnum.FEMALE)) {
            dbService.savePersonGender(chatId, buttonCommandEnum);
            sendMessage.setText(messageService.getMessage("bot.command.person.whatyourname"));
        } else if (buttonCommandEnum.equals(ButtonCommandEnum.MALE_SEARCH) || buttonCommandEnum.equals(ButtonCommandEnum.FEMALE_SEARCH) || buttonCommandEnum.equals(ButtonCommandEnum.ALL_SEARCH)) {
            dbService.savePersonOrientation(chatId, buttonCommandEnum);
            imageMessageDto.setSendPhoto(dbService.getAccountPicture(chatId));
            sendMessage = buttonService.getMenuButtons(chatId);
        }
        imageMessageDto.setSendMessage(sendMessage);

        return imageMessageDto;
    }

    public SendMessage parseInputText(String textCommand, long chatId) {

        if (!dbService.haveName(chatId)) {
            return parsePersonName(textCommand, chatId);
        } else if (!dbService.haveAge(chatId)) {
            return parseAge(textCommand, chatId);
        } else if (!dbService.haveHeader(chatId)) {
            return parseHeader(textCommand, chatId);
        } else {
            return parseAboutPerson(textCommand, chatId);
        }
    }

    private SendMessage parseHeader(String textCommand, long chatId) {
        log.info("Parse person header command");
        dbService.savePersonHeader(textCommand, chatId);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(messageService.getMessage("bot.command.person.persondescription"));
        return sendMessage;
    }

    private SendMessage parseAge(String textCommand, long chatId) {
        log.info("Parse person age command");
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        if (!textCommand.isEmpty() && textCommand.length() <= 3) {
            dbService.savePersonAge(Integer.parseInt(textCommand), chatId);
            sendMessage.setText(messageService.getMessage("bot.command.person.header"));
        } else {
            sendMessage.setText(messageService.getMessage(("message.bot.command.uncorrectage")));
        }
        return sendMessage;
    }

    private SendMessage parsePersonName(String personName, long chatId) {
        log.info("Parse person name command");
        dbService.savePersonName(personName, chatId);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(messageService.getMessage("bot.command.person.age"));
        return sendMessage;
    }

    private SendMessage parseAboutPerson(String textCommand, long chatId) {
        log.info("Parse about person command");
        dbService.saveAboutPersonInformation(textCommand, chatId);
        return buttonService.getButtonByCommand(ButtonCommandEnum.ABOUT, chatId);
    }
}
