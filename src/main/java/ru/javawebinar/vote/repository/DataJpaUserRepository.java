package ru.javawebinar.vote.repository;


import org.springframework.stereotype.Repository;
import ru.javawebinar.vote.model.User;

import java.util.List;

@Repository
public class DataJpaUserRepository  {
    private CrudUserRepository repository;
    public List<User> getAll(){
        return repository.findAll();
    }
}
