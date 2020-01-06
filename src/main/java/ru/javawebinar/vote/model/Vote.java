package ru.javawebinar.vote.model;

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
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restoran_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Restoran restoran;

    public Vote(){}

    public Vote(Integer id,Date registered){
        super(id);
        this.registered=registered;
    }

    public Vote(@NotNull User user, @NotNull Restoran restoran) {
        this.user = user;
        this.restoran = restoran;
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

    public Restoran getRestoran() {
        return restoran;
    }

    public void setRestoran(Restoran restoran) {
        this.restoran = restoran;
    }
}
