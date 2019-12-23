package ru.javawebinar.vote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.vote.model.Restoran;
import ru.javawebinar.vote.repository.RestoranRepo;

import java.util.List;

@Service
public class RestoranService {

    private final RestoranRepo repository;

    @Autowired
    public RestoranService(RestoranRepo repository) {
        this.repository = repository;
    }

    public List<Restoran> getAll() {
        return repository.findAll();
    }

    public Restoran get(Long id) {
        return repository.getById(id);
    }

    public Restoran create(Restoran restoran) {
        return repository.save(restoran);
    }
}
