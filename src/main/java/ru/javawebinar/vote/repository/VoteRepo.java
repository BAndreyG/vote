package ru.javawebinar.vote.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.javawebinar.vote.model.Vote;

@Repository
public interface VoteRepo extends JpaRepository<Vote, Integer> {
}
