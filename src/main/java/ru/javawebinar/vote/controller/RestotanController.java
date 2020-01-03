package ru.javawebinar.vote.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.vote.model.Restoran;
import ru.javawebinar.vote.model.User;
import ru.javawebinar.vote.service.RestoranService;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/restorans",produces = MediaType.APPLICATION_JSON_VALUE)
public class RestotanController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestoranService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restoran> getAll() {
        log.info("getAll restoran");
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Restoran get(@PathVariable int id) {
        log.info("get {}", id);
        return service.get(id);
    }
    @PostMapping
    public Restoran create(@RequestBody Restoran restoran){
        //Restoran created=service.create(restoran);
        return service.create(restoran);//ResponseEntity.created().body(created);
    }
}
