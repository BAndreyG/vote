package ru.javawebinar.vote.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.vote.TO.ResTo;
import ru.javawebinar.vote.TO.VoteTo;
import ru.javawebinar.vote.model.Menu;
import ru.javawebinar.vote.model.Restoran;
import ru.javawebinar.vote.model.User;
import ru.javawebinar.vote.model.Vote;
import ru.javawebinar.vote.repository.RestoranRepo;
import ru.javawebinar.vote.repository.VoteRepo;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class VoteService {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    private final VoteRepo repo;
    private final RestoranRepo repoRes;
    //private final RestoranRepo repoRes;

    @Autowired
    public VoteService(VoteRepo repo, RestoranRepo repoRes) {
        this.repo = repo;
        this.repoRes = repoRes;
    }

    public List<Restoran> getAll(){
        return repoRes.findAll();
    }

    public Restoran getTest(int id){
        return repoRes.getById(id);
    }

    public Set<Menu> get(int id) {
        log.info("get id =",id);
        ResTo resTo=new ResTo(repoRes.getById(id));
        resTo.setMenus(resTo.getMenus().stream()
                .filter(menu -> menu.isEnabled())
                .collect(Collectors.toSet()));
        System.out.println(resTo.getMenus());
        return resTo.getMenus();
    }

    public void create(User user, Restoran restoran) {
        Vote vote= new Vote(user,restoran);
        repo.save(vote);
    }

    public void update(Vote vote) {

    }
}
