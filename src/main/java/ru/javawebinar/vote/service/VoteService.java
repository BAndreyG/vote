package ru.javawebinar.vote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.vote.model.Vote;
import ru.javawebinar.vote.repository.VoteRepo;

import java.util.List;

@Service
public class VoteService {

    private final VoteRepo repo;

    @Autowired
    public VoteService(VoteRepo repo) {
        this.repo = repo;
    }

    public List<Vote> getAll(){
        return repo.findAll();
    }

    public Vote get(int id){
        return repo.getById(id);
    }

    public void create(Vote vote) {
    }

    public void update(Vote vote) {
    }
}
