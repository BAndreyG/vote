package ru.javawebinar.vote.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.javawebinar.vote.service.UserService;

//@RestController
@Controller
@RequestMapping(value ="api/v1/users")
public class UserController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService service;
}
