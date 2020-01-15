package ru.javawebinar.vote.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "votes")
public class Vote extends AbstractBaseEntity {
    @Column(name = "registered", nullable = false, columnDefinition = "timestamp default now()")
    @NotNull
    private Date registered;

    @OneToOne(cascade = CascadeType.ALL)
    @NotNull
    @JsonIgnore
    private User user;

    private int restoran_id;

    public Vote() {
    }

    public Vote(Integer id, Date registered) {
        super(id);
        this.registered = registered;
    }

    public Vote(@NotNull User user, @NotNull int restoran_id) {
        this.user = user;
        this.restoran_id = restoran_id;
        this.registered = new Date();
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
