package ru.javawebinar.vote.repository;

import ru.javawebinar.vote.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepo extends JpaRepository<Vote,Integer> {
    Vote getById(int id);
}
