package ru.javawebinar.vote.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.javawebinar.vote.model.Menu;
import ru.javawebinar.vote.model.Restoran;
import ru.javawebinar.vote.repository.RestoranRepo;

import java.util.Set;

import static ru.javawebinar.vote.util.ValidationUtil.checkNotFoundWithId;

@Service
public class RestoranService {
    protected final Logger log = LoggerFactory.getLogger(getClass());
    private final RestoranRepo repository;

    @Autowired
    public RestoranService(RestoranRepo repository) {
        this.repository = repository;
    }

    public Set<Menu> get(int id) {
        log.info("get id =", id);
        return repository.getMenu(id);
    }

    @Transactional
    public void update(Restoran restoran) {
        Assert.notNull(restoran, "restoran must not be null");
        repository.save(new Restoran(restoran));
    }

    public Restoran create(Restoran restoran) {
        Assert.notNull(restoran, "restoran must not be null");
        return repository.save(restoran);
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.deleteById(id),id);
    }
}
