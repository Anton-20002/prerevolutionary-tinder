package ru.digitalleague.prerevolutionarytinderdatabase.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(schema = "tinder", name = "blacklist")
@SequenceGenerator(schema = "tinder", name = "blacklist_s", sequenceName = "tinder.blacklist_s", allocationSize = 1)
public class BlackList {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "blacklist_s")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "person_id", nullable = false)
    private Long personId;
    @Column(name = "banned_person_id", nullable = false)
    private Long bannedPersonId;
}
