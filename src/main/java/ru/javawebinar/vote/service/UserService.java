package ru.javawebinar.vote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.javawebinar.vote.model.User;
import ru.javawebinar.vote.repository.UserRepo;

import java.util.List;

import static ru.javawebinar.vote.util.ValidationUtil.checkNotFoundWithId;

@Service

public class UserService {

    private final UserRepo repository;

    /*@Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
*/
    @Autowired
    public UserService(UserRepo repository) {
        this.repository = repository;
    }

    public User create(User user) {
        Assert.notNull(user, "user must not be null");
        //user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.deleteById(id),id);
    }

    public User get(int id) {
        return checkNotFoundWithId(repository.getById(id),id);
    }

    public List<User> getAll() {
        return repository.findAll();
    }

    @Transactional
    public void update(User user,int id) {
        User userUpdate=new User(get(id));
        Assert.notNull(user, "user must not be null");
      //  user.setPassword(bCryptPasswordEncoder.encode(userUpdate.getPassword()));
        repository.save(userUpdate);
    }

}
