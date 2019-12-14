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
        log.info("getALL {}");
        return service.getAll();
    }

    @GetMapping("/{id}")
    public User get(@PathVariable int id) {
        log.info("get {}", id);
        return service.get(id);
    }

    @GetMapping("/123")
    public String get() {
        log.info("get {}", 123);
        User u=new User();
        u.setEmail("sdf@er.ru");
        u.setId(123);
        u.setRegistered(new Date());
        u.setPassword("password");
        //u.setRoles([Role.ROLE_USER]);
        u.setName("sdfphgo");
       // u.setId(100123);
        service.create(u);
        return "123321"+u.toString();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete {}", id);
        service.delete(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createOrUpdate(@Valid User user) { //, BindingResult result
        /*if (result.hasErrors()) {
            return ValidationUtil.getError(result);
        }*/
        if (user.isNew()) {
            service.create(user);
        }
        else service.update(user, user.getId());
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@RequestBody User user, @PathVariable int id) {
        log.info("update {} with id={}", user, id);
        // assureIdConsistent(user, id);
        service.update(user,id);
    }


/*
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createWithLocation(@RequestBody User user) {
        log.info("create {}", user);
        if (user.isNew()) {
            User created = service.create(user);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
        }
        else throw new IllegalArgumentException(user + " must be new (id=null)");
    }



    /

   /* public User getByMail(String email) {
        log.info("getByEmail {}", email);
        return service.getByEmail(email);
    }
*/
    /*public void enable(int id, boolean enabled) {
        log.info(enabled ? "enable {}" : "disable {}", id);
        service.enable(id, enabled);
    }*/
}
