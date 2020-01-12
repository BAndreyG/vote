package ru.javawebinar.vote.service;

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

    private final RestoranRepo repository;

    @Autowired
    public RestoranService(RestoranRepo repository) {
        this.repository = repository;
    }

    public Set<Menu> get(int id) {
        if (repository.existsById(id)){
            return checkNotFoundWithId(repository.getMenu(id),id);
        }
        else System.out.println("error");
        return null;

    }

    @Transactional
    public void update(Restoran restoran,int id) {
        Assert.notNull(restoran, "restoran must not be null");
        repository.save(new Restoran(restoran));
    }

    public Restoran create(Restoran restoran) {
        Assert.notNull(restoran, "restoran must not be null");
        return repository.save(restoran);
    }

    public void delete(int id) {
        if (repository.existsById(id)){
      repository.deleteById(id);}
    }
}
