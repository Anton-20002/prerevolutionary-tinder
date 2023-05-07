package ru.digitalleague.prerevolutionarytinderserver.servicies;

import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.BufferedImage;

@Service
public class ImageService {

    public BufferedImage createImage(String header, String age, String description) {
        BufferedImage bufferedImage = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_RGB);

        Graphics graphics = bufferedImage.getGraphics();
        graphics.setFont(new Font("Old Standard TT", Font.BOLD, 50));

        graphics.drawString(header, 20, 100);

        graphics.setFont(new Font("Old Standard TT", Font.PLAIN, 14));
        graphics.drawString(age, 20, 300);
        graphics.drawString(description, 20, 400);

        return bufferedImage;
    }
}
