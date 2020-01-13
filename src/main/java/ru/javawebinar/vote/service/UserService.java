package ru.javawebinar.vote.service;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.domain.Sort;
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

@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserService implements UserDetailsService {  //

    private final UserRepo repository;
    private final PasswordEncoder passwordEncoder;

    /*@Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
*/
    @Autowired
    public UserService(UserRepo repository, PasswordEncoder passwordEncoder) {//     public UserService(UserRepo repository) {//,PasswordEncoder passwordEncoder
        this.passwordEncoder=passwordEncoder;
        this.repository = repository;
    }

    @Transactional
    public User create(User user) {
        Assert.notNull(user, "user must not be null");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        //user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

    public void delete(int id) {
        if (repository.existsById(id)){
        repository.deleteById(id);}
    }

    public User get(int id) {
        return repository.getById(id);
    }

    public List<User> getAll() {
        return repository.findAll(Sort.by("name"));
    }

    @Transactional
    public void update(User user,int id) {
        Assert.notNull(user, "user must not be null");
        User userUpdate=new User(get(id));
        userUpdate.setName(user.getName());
        userUpdate.setPassword(user.getPassword());
        userUpdate.setEmail(user.getEmail());
        userUpdate.setRoles(user.getRoles());

        user.setPassword(passwordEncoder.encode(userUpdate.getPassword()));
      //  user.setPassword(bCryptPasswordEncoder.encode(userUpdate.getPassword()));
        repository.save(userUpdate);
    }

    @Override
    public AuthorizedUser loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository.getByEmail(email.toLowerCase());
        if (user == null) {
            throw new UsernameNotFoundException("User " + email + " is not found");
        }
        return new AuthorizedUser(user);
    }
}
