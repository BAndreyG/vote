package ru.javawebinar.vote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.javawebinar.vote.AuthorizedUser;
import ru.javawebinar.vote.model.User;
import ru.javawebinar.vote.repository.UserRepo;

import java.util.List;

import static ru.javawebinar.vote.util.ValidationUtil.checkNotFoundWithId;

@Service

public class UserService implements UserDetailsService {

    private final UserRepo repository;
    private final PasswordEncoder passwordEncoder;

    /*@Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
*/
    @Autowired
    public UserService(UserRepo repository,PasswordEncoder passwordEncoder) {
        this.repository = repository;this.passwordEncoder=passwordEncoder;
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

    @Override
    public AuthorizedUser loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDetails userDetails;
        System.out.println(email);
        User user = repository.getByEmail(email.toLowerCase());
        if (user == null) {
            throw new UsernameNotFoundException("User " + email + " is not found");
        }
        return new AuthorizedUser(user);
    }
}
