package ru.digitalleague.prerevolutionarytinderdatabase.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.digitalleague.prerevolutionarytinderdatabase.entities.BlackList;

@Repository
public interface BlackListRepository extends JpaRepository<BlackList, Long> {

    @Query(value = "SELECT EXISTS (SELECT * FROM tinder.blacklist WHERE person_id = ?1 AND banned_person_id = ?2)", nativeQuery = true)
    public boolean containsByPersonIdAndBannedPersonId(Long personId, Long bannedPersonId);
}
