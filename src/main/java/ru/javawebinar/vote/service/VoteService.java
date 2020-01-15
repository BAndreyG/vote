package ru.javawebinar.vote.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
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

    protected final Logger log = LoggerFactory.getLogger(getClass());
    private final VoteRepo repo;
    private final RestoranRepo repoRes;
    private final UserRepo repoUser;

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
        return checkNotFoundWithId(repoRes.getMenu(id), id);
    }

    public Vote create(User user, int restoran_id) {
        repoRes.sumVoteIncrement(restoran_id);
        return repo.saveAndFlush(new Vote(user, restoran_id));
    }

    public Vote update(Vote vote, int restoran_id) {
        repoRes.sumVoteDecrement(vote.getRestoran());
        repoRes.sumVoteIncrement(restoran_id);
        return repo.saveAndFlush(new Vote(vote.getUser(), restoran_id));
    }

    public Vote createOrUpdate(User user, int restoran_id) {
        Vote newVote = voteIf(user, restoran_id);
        if (newVote != null) {
            user.setVote(newVote);
            repoUser.saveAndFlush(user);
            repoUser.changeVote(newVote.getId(), user.getId());
            System.out.println(newVote.getUser());
            return newVote;
        }
        return null;
    }

    public Vote voteIf(User user, int restoran_id) {
        Vote createdVote = user.getVote();
        if (createdVote != null) {
            System.out.println(createdVote.getRegistered().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().compareTo(LocalDate.now()));
            if (createdVote.getRegistered().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().compareTo(LocalDate.now()) == 0) {
                if (LocalTime.now().isAfter(LocalTime.of(11, 00))) {
                    if (createdVote.getRestoran() == restoran_id) {
                        log.info("Нельзя накручивать счётчик!");
                        return null;
                    }
                    return update(createdVote, restoran_id);
                }
            } else if (createdVote.getRegistered().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().compareTo(LocalDate.now()) < 0) {
                return create(user, restoran_id);
            }
            log.info("Голосовать уже поздно");
            return null;
        }
        return create(user, restoran_id);
    }
}
