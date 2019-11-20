package ru.javawebinar.vote.model;

import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
//import java.sql.Date;
import java.util.EnumSet;
import java.util.*;

@Entity
@Table (name = "users") //,uniqueConstraints = {@UniqueConstraint(columnNames = "name", name = "users_unique_email_idx")})
public class User extends AbstractNamedEntity{

    @Column (name="password",nullable = false)
    @Size(min=8,max=250)
    private String password;

    @Column(name = "registered",nullable = false,columnDefinition = "timestamp default now()")
    @NotNull
    private Date registered;

    @Column(name = "enabled", nullable = false, columnDefinition = "bool default true")
    private boolean enabled = true;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    @BatchSize(size = 200)
    private Set<Role> roles;

    @CollectionTable(name = "votes",joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "registered")
    @ElementCollection(fetch = FetchType.EAGER)
    @BatchSize(size = 200)
    private Date vote;

    public User(){}

    public User( User u){
        this(u.getId(), u.getName(),u.getPassword(), u.getRegistered(), u.isEnabled(), u.getRoles(),u.getVote());
    }

    public User(Integer id,String name, String password,Date registered,Role role,Role... roles){
        this(id,name,password,registered,true, EnumSet.of(role, roles), new Date());
    }

    public User(@Size(min = 8, max = 250) String password, @NotNull Date registered, boolean enabled, Set<Role> roles, Date vote) {
        this.password = password;
        this.registered = registered;
        this.enabled = enabled;
        this.roles = roles;
        this.vote = vote;
    }

    public User(Integer id, String name, @Size(min = 8, max = 250) String password, @NotNull Date registered, boolean enabled, Set<Role> roles, Date vote) {
        super(id, name);
        this.password = password;
        this.registered = registered;
        this.enabled = enabled;
        this.roles = roles;
        this.vote = vote;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Date getVote() {
        return vote;
    }

    public void setVote(Date vote) {
        this.vote = vote;
    }
}
