package ru.digitalleague.prerevolutionarytindertgbotclient.bot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private DbService dbService;

    public SendPhoto getPicture(long chatId) {
        List<Byte> accountPictureByteArray = dbService.getAccountPicture(chatId);
        byte[] bytes = new byte[accountPictureByteArray.size()];
        for (int i = 0; i < accountPictureByteArray.size(); i++) {
            bytes[i] = accountPictureByteArray.get(i);
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

        InputFile inputFile = new InputFile();
        inputFile.setMedia(outputfile);
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(chatId);
        sendPhoto.setPhoto(inputFile);

        return sendPhoto;
    }
}
