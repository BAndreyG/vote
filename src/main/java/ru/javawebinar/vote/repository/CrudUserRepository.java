package ru.javawebinar.vote.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javawebinar.vote.model.User;

public interface CrudUserRepository extends JpaRepository<User,Integer> {

}
