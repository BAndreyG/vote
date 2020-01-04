package ru.javawebinar.vote.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.vote.model.Restoran;
import ru.javawebinar.vote.model.Vote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javawebinar.vote.service.VoteService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/votes",produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private VoteService service;

    @Autowired
    public VoteController(VoteService service) {
        this.service = service;
    }

    @GetMapping
    public List<Vote> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Vote get(@PathVariable int id){
        return service.get(id);
    }

    @PostMapping()
    public ResponseEntity<String> createOrUpdate(@Valid Vote vote){
        if (vote.isNew()) service.create(vote);
        else service.update(vote);
        return ResponseEntity.ok().build();
    }

}
