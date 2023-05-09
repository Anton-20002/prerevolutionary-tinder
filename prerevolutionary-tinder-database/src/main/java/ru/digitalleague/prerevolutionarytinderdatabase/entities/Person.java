package ru.digitalleague.prerevolutionarytinderdatabase.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.digitalleague.prerevolutionarytinderdatabase.enums.Gender;
import ru.digitalleague.prerevolutionarytinderdatabase.enums.Orientation;

import java.util.List;

@Getter
@Setter
@Entity
@Table(schema = "tinder", name = "persons")
@SequenceGenerator(schema = "tinder", name = "persons_s", sequenceName = "tinder.persons_s", allocationSize = 1)
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "persons_s")
    @Column(name = "person_id", nullable = false)
    private Long id;

    @Column(name = "chat_id", nullable = false)
    private Long chatId;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "gender")
    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @Column(name = "orientation")
    @Enumerated(value = EnumType.STRING)
    private Orientation orientation;

    @Column(name = "age")
    private Integer age;

    @Column(name = "header")
    private String header;

    @Column(name = "description")
    private String description;

    @ManyToMany
    @JoinTable(
            name = "blacklist",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "banned_person_id"))
    private List<Person> blacklist;

    @ManyToMany
    @JoinTable(
            name = "favoritelist",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "favorite_person_id"))
    private List<Person> favorites;
}
