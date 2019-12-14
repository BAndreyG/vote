package ru.javawebinar.vote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.vote.model.Restoran;
import ru.javawebinar.vote.model.User;
import ru.javawebinar.vote.repository.DataJpaUserRepository;
import ru.javawebinar.vote.repository.RestoranRepository;

import java.util.List;

@Service
public class RestoranService {

    private final RestoranRepository repository;

    @Autowired
    public RestoranService(RestoranRepository repository) {
        this.repository = repository;
    }

    public List<Restoran> getAll() {
        return repository.getAll();
    }

    public Restoran get(int id) {
        return repository.get(id);
    }
}
