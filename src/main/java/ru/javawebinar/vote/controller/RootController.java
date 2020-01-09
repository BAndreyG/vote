package ru.javawebinar.vote.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javawebinar.vote.model.User;
import ru.javawebinar.vote.service.UserService;

import java.util.List;

@RestController
public class RootController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService service;

    @GetMapping("/")
    public String root() {
        return "redirect:votes";
    }

    @GetMapping("/votes")
    public String getVotes(){return "votes";}

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("api/v1/users") //, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAll() {
        log.info("getALL {} User");
        return service.getAll();
    }

    public String getUsers() {
        return "users";
    }

  /*  @GetMapping(value = "/login")
    public String login() {
        return "login";
    }
*/
}
