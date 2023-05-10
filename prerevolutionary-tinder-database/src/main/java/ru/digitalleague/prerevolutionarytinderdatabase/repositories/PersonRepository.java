package ru.digitalleague.prerevolutionarytinderdatabase.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.digitalleague.prerevolutionarytinderdatabase.entities.Person;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByChatId(Long chatId);

    @Modifying
    @Query(value = "SELECT p.* FROM tinder.persons p " +
            "WHERE person_id IN (SELECT person_id FROM tinder.favoritelist WHERE favorite_person_id = ?1) " +
            "AND person_id NOT IN (SELECT banned_person_id FROM tinder.blacklist WHERE person_id = ?1)",
            nativeQuery = true)
    List<Person> getFavoriteDatingProfilesByPersonId(Long personId);

    @Modifying
    @Query(value = "SELECT DISTINCT p.* FROM tinder.persons p " +
            "WHERE person_id IN (SELECT favorite_person_id FROM tinder.favoritelist WHERE person_id = ?1) " +
            "AND person_id IN (SELECT person_id FROM tinder.favoritelist WHERE favorite_person_id = ?1) " +
            "AND person_id NOT IN (SELECT banned_person_id FROM tinder.blacklist WHERE person_id = ?1)",
            nativeQuery = true)
    List<Person> getDistinctFavoriteDatingProfilesByPersonId(Long personId);

    @Modifying
    @Query(value = "SELECT p.* FROM tinder.persons p " +
            "WHERE person_id NOT IN (SELECT person_id FROM tinder.favoritelist WHERE favorite_person_id = ?1) " +
            "AND person_id NOT IN (SELECT favorite_person_id FROM tinder.favoritelist WHERE person_id = ?1) " +
            "AND person_id NOT IN (SELECT banned_person_id FROM tinder.blacklist WHERE person_id = ?1) " +
            "AND gender IN ?2 " +
            "AND orientation IN ?3",
            nativeQuery = true)
    List<Person> getAnotherDatingProfilesByPersonId(Long personId, List<String> genders, List<String> orientations);
}
