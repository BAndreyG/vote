package ru.javawebinar.vote.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.vote.model.Menu;
import ru.javawebinar.vote.model.User;
import ru.javawebinar.vote.service.MenuService;
import ru.javawebinar.vote.service.UserService;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/menus",produces = MediaType.APPLICATION_JSON_VALUE)
public class MenuControler {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MenuService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Menu> getAll() {
        log.info("getALL {} , menu");
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Menu get(@PathVariable int id){
        log.info("get {}", id);
        return service.get(id);
    }

}
