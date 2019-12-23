package ru.javawebinar.vote.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javawebinar.vote.model.User;

import java.util.List;

public interface UserRepo extends JpaRepository<User,Integer> {
    User getById(int id);
    User getByEmail(String email);
    void deleteById(int id);
}
