package ru.digitalleague.prerevolutionarytinderdatabase.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.digitalleague.prerevolutionarytinderdatabase.entities.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
