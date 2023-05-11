package ru.digitalleague.prerevolutionarytindertgbotclient.bot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.digitalleague.prerevolutionarytinderdatabase.dtos.FavoritePersonDto;
import ru.digitalleague.prerevolutionarytindertgbotclient.bot.entity.ImageMessageDto;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class FavoritesService {

    @Autowired
    private DbService dbService;

    @Autowired
    private PictureService pictureService;

    //проверить на пустой лист
    public List<ImageMessageDto> getFavorites(long chatId){
        List<FavoritePersonDto> favoritesList = dbService.getFavoritesByChatId(chatId);
        List<ImageMessageDto> imageMessageDtoList = new ArrayList<>();

        for (int i = 0; i < favoritesList.size(); i++){
            ImageMessageDto imageMessageDto = new ImageMessageDto();
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(chatId);
            sendMessage.setText(favoritesList.get(i).getRomanceStatus().getRussianName());
            File picture = pictureService.createPicture(favoritesList.get(i).getId(), favoritesList.get(i).getImageFile());
            imageMessageDto.setSendPhoto(pictureService.getSendPhoto(chatId, picture));
            imageMessageDto.setSendMessage(sendMessage);
            imageMessageDtoList.add(imageMessageDto);
        }
        return imageMessageDtoList;
    }
}
