package ru.javawebinar.vote.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {

    @GetMapping("/")
    public String root() {
        return "redirect:votes";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("api/v1/users")
    public String getUsers() {
        return "users";
    }

  /*  @GetMapping(value = "/login")
    public String login() {
        return "login";
    }
*/
}
