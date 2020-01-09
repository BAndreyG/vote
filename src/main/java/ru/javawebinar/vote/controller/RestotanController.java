package ru.javawebinar.vote.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.vote.model.Menu;
import ru.javawebinar.vote.model.Restoran;
import ru.javawebinar.vote.service.RestoranService;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@PreAuthorize("hasRole('ADMIN')")
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
    public Set<Menu> get(@PathVariable int id) {
        log.info("get {}", id);
        return service.get(id);
    }
    @PostMapping
    public ResponseEntity<String> createOrUpdate(@Valid Restoran restoran){
        if (restoran.isNew()) service.create(restoran);
        else service.update(restoran);
        return ResponseEntity.ok().build();
    }
}
