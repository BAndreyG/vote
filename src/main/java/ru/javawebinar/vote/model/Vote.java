package ru.javawebinar.vote.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "votes")
public class Vote extends AbstractBaseEntity {
    @Column(name = "registered",nullable = false,columnDefinition = "timestamp default now()")
    @NotNull
    private Date registered;
/*
    @CollectionTable(name = "users", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "user")
    @ElementCollection(fetch = FetchType.EAGER)
    private String user_id;

    @CollectionTable(name = "restorans", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "restoran")
    @ElementCollection(fetch = FetchType.EAGER)
    private String restoran;
*/
    @OneToOne()//fetch = FetchType.EAGER  optional = false,,fetch = FetchType.EAGER mappedBy = "vote"
   // @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    @JsonIgnore
    private User user;

    /*@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restoran_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Restoran restoran;*/
    private int restoran_id;

    public Vote(){}

    public Vote(Integer id,Date registered){
        super(id);
        this.registered=registered;
    }

    public Vote(@NotNull User user, @NotNull int restoran_id) {
        //проверить какое ИД
        this.user = user;
        this.restoran_id = restoran_id;
        this.registered=new Date();
    }


    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getRestoran() {
        return restoran_id;
    }

    public void setRestoran(int restoran) {
        this.restoran_id = restoran;
    }
}
