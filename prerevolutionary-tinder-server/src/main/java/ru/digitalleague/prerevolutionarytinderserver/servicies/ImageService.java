package ru.digitalleague.prerevolutionarytinderserver.servicies;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class ImageService {

    @Value("${file.path.name}")
    public String filePathName;
    private static final int STRING_SIZE = 50;
    private static final List<String> wordEndings = List.of("б ", "в ", "г ", "д ", "ж ", "з ", "к ", "л ", "м ", "н ", "п ", "р ", "с ", "т ", "ф ", "х ", "ц ", "ч ", "ш ", "щ ");
    private static final List<String> iVowels = List.of("иа", "ие", "иё", "ии", "ио", "иу", "иы", "иэ", "ию", "ия", "Иа", "Ие", "Иё", "Ии", "Ио", "Иу", "Иы", "Иэ", "Ию", "Ия");

    public List<Byte> createImage(Long id, String header, String age, String description) {
        BufferedImage bufferedImage = null;
        InputStream resourceAsStream = ImageService.class.getClassLoader().getResourceAsStream(filePathName);
        try {
            bufferedImage = ImageIO.read(resourceAsStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Graphics2D graphics = bufferedImage.createGraphics();
        graphics.setColor(Color.DARK_GRAY);

        graphics.setFont(new Font("Old Standard TT", Font.BOLD, 50));

        header = header.replace('ф', 'ѳ');
        header = header.replace('Ф', 'ѳ');
        graphics.drawString(header, 20, 100);

        graphics.setFont(new Font("Old Standard TT", Font.PLAIN, 20));
        graphics.drawString(age + " лѣтъ", 30, 150);

        description = translateDescription(description);

        if (description.length() <= STRING_SIZE) {
            graphics.drawString(description, 30, 200);
        } else {
            List<String> descriptions = getDescriptionList(description);
            for (int i = 0; i < descriptions.size(); i++) {
                graphics.drawString(descriptions.get(i), 30, 200 + 30*i);

            }
        }



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

    private String translateDescription(String description) {
        for (String wordEnding : wordEndings) {
            description = description.replaceAll(wordEnding, wordEnding.substring(0, 1) + "ъ ");
        }
        for (String iVowel : iVowels) {
            description = description.replaceAll(iVowel, "i" + iVowel.substring(1, 2));
        }

        return description;
    }

    private static List<String> getDescriptionList(String description) {
        List<String> descriptions = new ArrayList<>();
        while (description.length() > STRING_SIZE) {
            for (int i = 50; i > 0; i--) {
                char c = description.charAt(i);
                if (c == ' ') {
                    String newString = description.substring(0, i);
                    description = description.substring(i+1, description.length());
                    descriptions.add(newString);
                    break;
                }
            }
        }
        descriptions.add(description);
        return descriptions;
    }
}
