package ru.javawebinar.vote.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.javawebinar.vote.model.User;


@Repository
public interface UserRepo extends JpaRepository<User,Integer> {
    User getById(int id);
    User getByEmail(String email);
}
