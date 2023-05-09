package ru.digitalleague.prerevolutionarytinderserver.servicies;

import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

@Service
public class ImageService {

    public byte[] createImage(Long id, String header, String age, String description) {
        BufferedImage bufferedImage = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_RGB);

        Graphics graphics = bufferedImage.getGraphics();
        graphics.setFont(new Font("Old Standard TT", Font.BOLD, 50));

        graphics.drawString(header, 20, 100);

        graphics.setFont(new Font("Old Standard TT", Font.PLAIN, 14));
        graphics.drawString(age, 20, 300);
        graphics.drawString(description, 20, 400);

        ByteArrayOutputStream bas = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, "png", bas);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        byte[] byteArray = bas.toByteArray();

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
