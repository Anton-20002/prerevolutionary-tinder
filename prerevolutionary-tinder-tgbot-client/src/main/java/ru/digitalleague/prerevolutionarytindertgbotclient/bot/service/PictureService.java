package ru.digitalleague.prerevolutionarytindertgbotclient.bot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Slf4j
@Service
public class PictureService {

    private final DbService dbService;

    public PictureService(DbService dbService) {
        this.dbService = dbService;
    }

    public SendPhoto getPicture(long chatId) {
        List<Byte> accountPictureByteArray = dbService.getAccountPicture(chatId);
        File picture = createPicture(chatId, accountPictureByteArray);

        return getSendPhoto(chatId, picture);
    }

    public SendPhoto getSendPhoto(long chatId, File picture) {
        InputFile inputFile = new InputFile();
        inputFile.setMedia(picture);
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(chatId);
        sendPhoto.setPhoto(inputFile);
        return sendPhoto;
    }

    public File createPicture(long chatId, List<Byte> listBytes){
        byte[] bytes = new byte[listBytes.size()];
        for (int i = 0; i < listBytes.size(); i++) {
            bytes[i] = listBytes.get(i);
        }
        InputStream inputStream = new ByteArrayInputStream(bytes);

        BufferedImage image = null;
        try {
            image = ImageIO.read(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        File outputfile = new File("info" + chatId + ".png");

        try {
            ImageIO.write(image, "png", outputfile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return outputfile;
    }
}
