package ru.javawebinar.vote.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.vote.TO.ResTo;
import ru.javawebinar.vote.model.Restoran;
import ru.javawebinar.vote.repository.RestoranRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestoranService {
    protected final Logger log = LoggerFactory.getLogger(getClass());
    private final RestoranRepo repository;

    @Autowired
    public RestoranService(RestoranRepo repository) {
        this.repository = repository;
    }

    public List<Restoran> getAll() {
        return repository.findAll();
    }

    public ResTo get(int id) {
        log.info("get id =",id);
        ResTo resTo=new ResTo(repository.getById(id));
        resTo.setMenus(resTo.getMenus().stream()
                .filter(menu -> menu.isEnabled())
                .collect(Collectors.toSet()));
        return resTo;
    }

    public void update(Restoran restoran){
        Restoran createRes=new Restoran(restoran);
    }
    public Restoran create(Restoran restoran) {
        return repository.save(restoran);
    }
}
