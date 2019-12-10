package ru.javawebinar.vote.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javawebinar.vote.model.User;

import java.util.List;

@Repository
public class DataJpaUserRepository {
    @Autowired
    private CrudUserRepository repository;

    public List<User> getAll() {
        return repository.findAll();
    }

    public boolean delete(int id) {
        return repository.deleteById(id) != 0;
    }

    public User findId(int id) {
        return repository.findById(id);
    }

    public User save(User user){
        return repository.save(user);
    }

}
