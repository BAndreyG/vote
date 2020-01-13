package ru.javawebinar.vote.repository;

import org.springframework.data.jpa.repository.Query;
import ru.javawebinar.vote.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepo extends JpaRepository<Vote,Integer> {
    Vote findByUserId(int id);
    Vote getVoteByUserId(int id);

    @Query("SELECT v FROM Vote v WHERE v.user.id=:id")
    Vote getVoteByUser_id(int id);
}
