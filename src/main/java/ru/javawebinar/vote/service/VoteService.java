package ru.javawebinar.vote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.vote.model.Restoran;
import ru.javawebinar.vote.model.Vote;
import ru.javawebinar.vote.repository.RestoranRepo;
import ru.javawebinar.vote.repository.VoteRepo;

import java.util.List;

@Service
public class VoteService {

    //private final VoteRepo repo;
    private final RestoranRepo repoRes;

    @Autowired
    public VoteService(RestoranRepo repoRes) {
        this.repoRes = repoRes;
    }

    public List<Restoran> getAll(){
        return repoRes.findAll();
    }

    public Restoran get(int id){
        return repoRes.getById(id);
    }

    public void create(Vote vote) {
    }

    public void update(Vote vote) {
    }
}
