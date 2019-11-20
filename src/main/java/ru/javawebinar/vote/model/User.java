package ru.javawebinar.vote.model;

import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.Set;

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



}
