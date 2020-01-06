package ru.javawebinar.vote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.vote.TO.VoteTo;
import ru.javawebinar.vote.model.Restoran;
import ru.javawebinar.vote.model.User;
import ru.javawebinar.vote.model.Vote;
import ru.javawebinar.vote.repository.RestoranRepo;
import ru.javawebinar.vote.repository.VoteRepo;

import java.util.List;

@Service
public class VoteService {

    private final VoteRepo repo;
    private final RestoranRepo repoRes;

    @Autowired
    public VoteService(VoteRepo repo, RestoranRepo repoRes) {
        this.repo = repo;
        this.repoRes = repoRes;
    }

    public List<Restoran> getAll(){
        return repoRes.findAll();
    }

    public Restoran get(int id){
        return repoRes.getById(id);
    }

    public void create(User user, Restoran restoran) {
        Vote vote= new Vote(user,restoran);
        repo.save(vote);
    }

    public void update(Vote vote) {

    }
}
