package ru.javawebinar.vote.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.vote.AuthorizedUser;
import ru.javawebinar.vote.HasId;
import ru.javawebinar.vote.TO.UserTo;
import ru.javawebinar.vote.model.Restoran;
import ru.javawebinar.vote.model.User;
import ru.javawebinar.vote.model.Vote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javawebinar.vote.service.VoteService;
import ru.javawebinar.vote.util.UserUtil;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
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
    public List<Restoran> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Restoran get(@PathVariable int id){
        return service.get(id);
    }

    @PostMapping(value ="/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<String> createOrUpdate(@Valid Restoran restoran){
        int userId=SecurityUtil.authUserId();
        UserTo userTo=SecurityUtil.get().getUserTo();
        Vote vote=SecurityUtil.get().getUserTo().getVote();
        //у зарегано пользователя узнать наличие голоса , его дату, сравнитьс сегодняшней датой, если сегодня то сейчас не позже ли 11-00, если нет,то апдате голос
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

}
