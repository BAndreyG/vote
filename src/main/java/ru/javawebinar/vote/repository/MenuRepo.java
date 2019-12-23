package ru.javawebinar.vote.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javawebinar.vote.model.Menu;

public interface MenuRepo extends JpaRepository<Menu,Integer> {
    Menu findById(int id);
}
