package ru.digitalleague.prerevolutionarytinderdatabase.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.digitalleague.prerevolutionarytinderdatabase.entities.BlackList;

@Repository
public interface BlackListRepository extends JpaRepository<BlackList, Long> {
}
