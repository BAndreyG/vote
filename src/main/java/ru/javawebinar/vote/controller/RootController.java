package ru.javawebinar.vote.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.vote.TO.UserTo;
import ru.javawebinar.vote.model.Menu;
import ru.javawebinar.vote.model.Restoran;
import ru.javawebinar.vote.model.User;
import ru.javawebinar.vote.model.Vote;
import ru.javawebinar.vote.service.UserService;
import ru.javawebinar.vote.service.VoteService;
import ru.javawebinar.vote.util.UserUtil;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Set;

@RestController
@PreAuthorize("hasRole('ADMIN')|| hasRole('USER')")
@RequestMapping(value = "/",produces = MediaType.APPLICATION_JSON_VALUE)
public class RootController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService serviceUser;

    @Autowired
    private VoteService service;

   /* @Autowired
    public VoteController(VoteService service) {
        this.service = service;
    }*/

    @GetMapping
    public List<Restoran> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Set<Menu> get(@PathVariable int id){
        return service.get(id);
    }

    @PostMapping(value ="/{id}")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<String> createOrUpdate(@Valid Restoran restoran){
        int userId=SecurityUtil.authUserId();
        UserTo userTo=SecurityUtil.get().getUserTo();
        Vote vote=SecurityUtil.get().getUserTo().getVote();
        if (vote!=null){
            LocalDateTime voteTime=vote.getRegistered().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            if (voteTime.toLocalDate().compareTo(LocalDate.now())==0){
                if (LocalTime.now().isBefore(LocalTime.of(11,00)))service.update(vote);
            }
            log.info("Голосовать уже поздно");
            return ResponseEntity.badRequest().build();
        }
        else service.create(UserUtil.createNewFromTo(userTo),restoran);
        return ResponseEntity.ok().build();
    }

/*    @RolesAllowed(value={"ROLE_SUPER_USER", "ROLE_ADMIN"})
    @GetMapping("/1")
    public String get1(){
        return "@RolesAllowed(value={\"ROLE_SUPER_USER\", \"ROLE_ADMIN\"})";
    }

    @PreAuthorize("hasRole('ADMIN')|| hasRole('ROLE_ADMIN')")//|| hasRole('USER')
    @GetMapping("/2")
    public String get2(){
        return "@PreAuthorize(\"hasRole('ADMIN') || hasRole('SUPER_USER') || hasRole('USER')\")";
    }

    @Secured(value={"ROLE_ADMIN"})
    @GetMapping("/3")
    public String get3(){
        return "@Secured(value={\"ROLE_ADMIN\"})";
    }*/

/*    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("api/v1/users") //, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAll() {
        log.info("getALL {} User");
        return service.getAll();
    }*/

  /*  public String getUsers() {
        return "users";
    }*/

  /*  @GetMapping(value = "/login")
    public String login() {
        return "login";
    }
*/
}
