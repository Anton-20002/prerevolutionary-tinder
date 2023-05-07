package ru.digitalleague.prerevolutionarytinderdatabase.dtos;

import lombok.Getter;
import lombok.Setter;
import ru.digitalleague.prerevolutionarytinderdatabase.enums.Gender;
import ru.digitalleague.prerevolutionarytinderdatabase.enums.Orientation;

import java.awt.image.BufferedImage;

@Getter
@Setter
public class PersonDto {
    private Long id;
    private String nickname;
    private Gender gender;
    private Orientation orientation;
    private String age;
    private String header;
    private String description;
    private BufferedImage image;
}
