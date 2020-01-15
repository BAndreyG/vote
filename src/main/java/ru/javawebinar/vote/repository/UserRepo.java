package ru.javawebinar.vote.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.vote.model.User;


@Repository
@Transactional
public interface UserRepo extends JpaRepository<User, Integer> {
    User getById(int id);

    User getByEmail(String email);

    @Transactional()
    @Modifying
    @Query("UPDATE User set vote_id=:id WHERE id=:user_id ")
    void changeVote(int id, int user_id);


}
