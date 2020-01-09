package ru.javawebinar.vote.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.vote.model.Role;
import ru.javawebinar.vote.model.User;
import ru.javawebinar.vote.service.UserService;
import ru.javawebinar.vote.util.ValidationUtil;

import javax.validation.Valid;
import java.net.URI;
import java.sql.Time;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/users",produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAll() {
        log.info("getALL {} User");
        return service.getAll();
    }

    @GetMapping("/{id}")
    public User get(@PathVariable int id) {
        log.info("get {}", id);
        return service.get(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete {}", id);
        service.delete(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createOrUpdate(@Valid User user,BindingResult result) { //, BindingResult result
        if (result.hasErrors()) {
            return ValidationUtil.getError(result);
        }
        if (user.isNew()) {
            service.create(user);
        }
        else service.update(user, user.getId());
        return ResponseEntity.ok().build();
    }
}
