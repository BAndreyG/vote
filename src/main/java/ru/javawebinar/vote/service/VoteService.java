package ru.javawebinar.vote.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.vote.model.Menu;
import ru.javawebinar.vote.model.Restoran;
import ru.javawebinar.vote.model.User;
import ru.javawebinar.vote.model.Vote;
import ru.javawebinar.vote.repository.RestoranRepo;
import ru.javawebinar.vote.repository.UserRepo;
import ru.javawebinar.vote.repository.VoteRepo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Set;

import static ru.javawebinar.vote.util.ValidationUtil.checkNotFoundWithId;

@Service
public class VoteService {

    private final VoteRepo repo;
    private final RestoranRepo repoRes;
    private final UserRepo repoUser;

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    public VoteService(VoteRepo repo, RestoranRepo repoRes, UserRepo repoUser) {
        this.repo = repo;
        this.repoRes = repoRes;
        this.repoUser = repoUser;
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
    public Vote create(Vote vote, int restoran_id) {
        repoRes.sumVoteIncrement(restoran_id);
        return repo.save(new Vote(vote.getUser(), restoran_id));
    }

    @Transactional
    public Vote update(Vote vote, int restoran_id) {
        repoRes.sumVoteDecrement(vote.getRestoran());
        repoRes.sumVoteIncrement(restoran_id);
        return repo.save(new Vote(vote.getUser(), restoran_id));
    }

    @Transactional
    public void savUs(User user){
        repoUser.save(user);
    }

    @Transactional
    public User getUser(int id){
        return repoUser.getById(id);
    }

    @Transactional
    public Vote createOrUpdate(int user_id, int restoran_id) {
        //Assert.notNull(user, "user must not be null");
        User user=getUser(user_id);
        Vote newVote=voteIf(user.getVote(),restoran_id);
        if (newVote!=null){
            user.setVote(newVote);
            savUs(user);
            return newVote;
        }
        return null;
    }


    public Vote voteIf(Vote createdVote,int restoran_id){
        if (createdVote != null) {
            if (createdVote.getRegistered().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().compareTo(LocalDate.now()) == 0) {
                if (LocalTime.now().isAfter(LocalTime.of(11, 00))){
                    if (createdVote.getRestoran()==restoran_id){
                        log.info("Нельзя накручивать счётчик!");
                        return null;
                    }
                    return update(createdVote, restoran_id);
                }
            }else if (createdVote.getRegistered().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().compareTo(LocalDate.now()) < 0){
                return create(createdVote, restoran_id);
            }
            log.info("Голосовать уже поздно");
            return null;
        }
        return create(createdVote, restoran_id);
    }
}
