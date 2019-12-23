package ru.javawebinar.vote.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.javawebinar.vote.model.User;

import java.util.List;

@Repository
public class UserRepositoryImpl {
    private static final Sort SORT_NAME_EMAIL = Sort.by(Sort.Direction.ASC, "name", "email");
    @Autowired
    private UserRepository repository;

    public List<User> getAll() {
        return repository.findAll();
    }//(SORT_NAME_EMAIL)

    public boolean delete(int id) {
        return repository.deleteById(id) != 0;
    }

    public User get(int id) {
        return repository.findById(id);
    }

    public User save(User user){
        return repository.save(user);
    }

    public User getByEmail(String email) {
        return repository.getByEmail(email);
    }
}
