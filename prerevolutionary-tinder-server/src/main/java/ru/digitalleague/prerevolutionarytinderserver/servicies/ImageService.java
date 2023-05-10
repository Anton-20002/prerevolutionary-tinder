package ru.digitalleague.prerevolutionarytinderserver.servicies;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Arrays;
import java.util.List;

@Service
public class ImageService {

    public List<Byte> createImage(Long id, String header, String age, String description) {
        BufferedImage bufferedImage = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_RGB);

        Graphics2D graphics = bufferedImage.createGraphics();
        graphics.setBackground(new Color(160, 130, 100));
        graphics.setColor(Color.DARK_GRAY);

        graphics.setFont(new Font("Old Standard TT", Font.BOLD, 75));

        graphics.drawString(header, 20, 100);

        graphics.setFont(new Font("Old Standard TT", Font.PLAIN, 30));
        graphics.drawString(age, 20, 300);
        graphics.drawString(description, 20, 400);

        ByteArrayOutputStream bas = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, "png", bas);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<Byte> byteArray =  Arrays.asList(ArrayUtils.toObject(bas.toByteArray()));

//        InputStream inputStream = new ByteArrayInputStream(byteArray);
//        BufferedImage image = null;
//        try {
//            image = ImageIO.read(inputStream);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        File outputfile = new File("info" + id.toString() + ".png");
//
//        try {
//            ImageIO.write(image, "png", outputfile);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        return byteArray;
    }
}
