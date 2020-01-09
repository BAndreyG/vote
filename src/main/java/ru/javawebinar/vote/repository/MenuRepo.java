package ru.javawebinar.vote.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.javawebinar.vote.model.Menu;

@Repository
public interface MenuRepo extends JpaRepository<Menu,Integer> {
    Menu findById(int id);
}
