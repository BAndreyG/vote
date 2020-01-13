package ru.javawebinar.vote.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javawebinar.vote.TO.UserTo;
import ru.javawebinar.vote.model.Menu;
import ru.javawebinar.vote.model.Restoran;
import ru.javawebinar.vote.model.User;
import ru.javawebinar.vote.model.Vote;
import ru.javawebinar.vote.service.VoteService;
import ru.javawebinar.vote.util.UserUtil;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Set;

@RestController
@PreAuthorize("hasRole('ADMIN')|| hasRole('USER')")
@RequestMapping(value = RootController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RootController {

    static final String REST_URL = "/";

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private VoteService service;

    @GetMapping
    public List<Restoran> getAll() {
        log.info("getAll votes");
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Set<Menu> get(@PathVariable int id) {
        log.info("get menu restoran_id = ", id);
        return service.get(id);
    }

    @PostMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Vote> createOrUpdate(@RequestBody User user, @PathVariable int id) {
        int userId = SecurityUtil.authUserId();
        UserTo userTo = SecurityUtil.get().getUserTo();
        Vote vote = SecurityUtil.get().getUserTo().getVote();
        Vote created=service.createOrUpdate(100001,id);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }


}
