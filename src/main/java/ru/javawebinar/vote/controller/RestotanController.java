package ru.javawebinar.vote.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javawebinar.vote.model.Menu;
import ru.javawebinar.vote.model.Restoran;
import ru.javawebinar.vote.model.User;
import ru.javawebinar.vote.repository.RestoranRepo;
import ru.javawebinar.vote.service.RestoranService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Set;

import static ru.javawebinar.vote.util.ValidationUtil.checkNotFoundWithId;

@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping(value =RestotanController.REST_URL,produces = MediaType.APPLICATION_JSON_VALUE)
public class RestotanController {

    static final String REST_URL = "/api/v1/restorans";

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestoranRepo repo;

    @Autowired
    private RestoranService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restoran> getAll() {
        log.info("getAll restoran");
        return repo.findAll(Sort.by("name"));
    }

    @GetMapping("/{id}")
    public Set<Menu> get(@PathVariable int id) {
        log.info("get {} restoran_id = ", id);
        return service.get(id);
    }
    /*@PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<String> createOrUpdate(@Valid Restoran restoran){
        if (restoran.isNew()) service.create(restoran);
        else service.update(restoran);
        return ResponseEntity.ok().build();
    }*/

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        service.delete(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restoran> createWithLocation(@RequestBody Restoran restoran) {
        Restoran created = service.create(restoran);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@RequestBody Restoran restoran, @PathVariable int id) {
        service.update(restoran, id);
    }
}
