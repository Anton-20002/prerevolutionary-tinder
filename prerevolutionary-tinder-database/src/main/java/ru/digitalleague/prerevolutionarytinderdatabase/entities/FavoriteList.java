package ru.digitalleague.prerevolutionarytinderdatabase.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.digitalleague.prerevolutionarytinderdatabase.enums.RomanceStatus;

@Getter
@Setter
@Entity
@Table(schema = "tinder", name = "favoritelist")
@SequenceGenerator(schema = "tinder", name = "favoritelist_s", sequenceName = "tinder.favoritelist_s", allocationSize = 1)
public class FavoriteList {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "person_id", nullable = false)
    private Long personId;
    @Column(name = "favorite_person_id", nullable = false)
    private Long bannedPersonId;
    @Column(name = "romance_status", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private RomanceStatus romanceStatus;
}
