package ru.javawebinar.vote.service;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.javawebinar.vote.model.User;
import ru.javawebinar.vote.repository.UserRepo;

import java.util.List;

@Service
public class UserService {

    private final UserRepo repository;

    @Autowired
    public UserService(UserRepo repository) {
        this.repository = repository;
    }

    public User create(User user) {
        Assert.notNull(user, "user must not be null");
        return repository.save(user);
    }

    public void delete(int id) {
        repository.deleteById(id);
    }

    public User get(int id) {
        return repository.getById(id);
    }

    public List<User> getAll() {
        return repository.findAll();
    }

    public void update(User user,int id) {
        User userUpdate=new User(get(id));
        Assert.notNull(user, "user must not be null");
        repository.save(userUpdate);
    }

    public User getByEmail(String email) {
        return repository.getByEmail(email);
    }

}
