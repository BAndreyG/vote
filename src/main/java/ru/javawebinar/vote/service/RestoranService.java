package ru.javawebinar.vote.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.vote.model.Menu;
import ru.javawebinar.vote.model.Restoran;
import ru.javawebinar.vote.repository.RestoranRepo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RestoranService {
    protected final Logger log = LoggerFactory.getLogger(getClass());
    private final RestoranRepo repository;

    @Autowired
    public RestoranService(RestoranRepo repository) {
        this.repository = repository;
    }

    public List<Restoran> getAll() {
        List<Restoran> restoranList=repository.findAll().stream()
                .forEach(restoran -> );
        Map<String,List<Menu>> mapRes=new HashMap<>();

        for (:
             ) {

        }

        return
    }

    public Restoran get(int id) {
        log.info("get id =",id);
        return repository.getById(id);
    }

    public void update(Restoran restoran){
        Restoran createRes=new Restoran(restoran);
    }
    public Restoran create(Restoran restoran) {
        return repository.save(restoran);
    }
}
