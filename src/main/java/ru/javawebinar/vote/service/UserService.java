package ru.javawebinar.vote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.javawebinar.vote.AuthorizedUser;
import ru.javawebinar.vote.model.User;
import ru.javawebinar.vote.repository.CrudUserRepository;

import java.util.List;

@Service
public class UserService {

    private final CrudUserRepository repository;

    @Autowired
    public UserService(CrudUserRepository repository) {
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
        return repository.get(id);
    }

    public List<User> getAll() {
        return repository.getAll();
    }

    public void update(User user) {
        Assert.notNull(user, "user must not be null");
        repository.save(user);
    }

    public AuthorizedUser loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository.getByEmail(email.toLowerCase());
        if (user == null) {
            throw new UsernameNotFoundException("User " + email + " is not found");
        }
        //return new AuthorizedUser(user);
        return null;
    }

    public User getByEmail(String email) {
        return repository.getByEmail(email);
    }

}
