package ru.javawebinar.vote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.javawebinar.vote.model.Menu;
import ru.javawebinar.vote.model.Restoran;
import ru.javawebinar.vote.model.User;
import ru.javawebinar.vote.model.Vote;
import ru.javawebinar.vote.repository.RestoranRepo;
import ru.javawebinar.vote.repository.VoteRepo;
import ru.javawebinar.vote.util.exception.NotFoundException;

import java.util.List;
import java.util.Set;

import static ru.javawebinar.vote.util.ValidationUtil.checkNotFoundWithId;

@Service
public class VoteService {

    private final VoteRepo repo;
    private final RestoranRepo repoRes;

    @Autowired
    public VoteService(VoteRepo repo, RestoranRepo repoRes) {
        this.repo = repo;
        this.repoRes = repoRes;
    }

    public List<Restoran> getAll() {
        return repoRes.findAll(Sort.by("name"));
    }

    public Set<Menu> get(int id) {
        return checkNotFoundWithId(repoRes.getMenu(id),id);/*
        if (repoRes.existsById(id)){
            repoRes.getMenu(id);
        }*/
       // return  null;
//        throw new NotFoundException("Restoran " + id + " is not found");
        //throw new NotFoundException("Not found entity with " + msg);   return checkNotFoundWithId(
    }

    @Transactional
    public void create(User user, int restoran_id) {
        Assert.notNull(user, "user must not be null");
        repoRes.sumVoteIncrement(restoran_id);
        repo.save(new Vote(user, restoran_id));
    }

    @Transactional
    public void update(Vote vote, int restoran_id) {
        repoRes.sumVoteDecrement(vote.getRestoran());
        repoRes.sumVoteIncrement(restoran_id);
        repo.save(new Vote(vote.getUser(), restoran_id));
    }
}
