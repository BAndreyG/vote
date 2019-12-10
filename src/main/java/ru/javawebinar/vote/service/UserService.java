package ru.javawebinar.vote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.vote.repository.CrudUserRepository;

@Service
public class UserService {

    private final CrudUserRepository repository;
    @Autowired
    public UserService(CrudUserRepository repository) {
        this.repository = repository;
    }
}
