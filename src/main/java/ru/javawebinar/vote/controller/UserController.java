package ru.javawebinar.vote.controller;

import org.springframework.beans.factory.annotation.Autowired;
import ru.javawebinar.vote.service.UserService;

public class UserController {

    @Autowired
    private UserService service;
}
