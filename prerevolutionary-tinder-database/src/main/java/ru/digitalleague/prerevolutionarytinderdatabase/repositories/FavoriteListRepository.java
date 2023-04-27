package ru.digitalleague.prerevolutionarytinderdatabase.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.digitalleague.prerevolutionarytinderdatabase.entities.FavoriteList;

@Repository
public interface FavoriteListRepository extends JpaRepository<FavoriteList, Long> {
}
