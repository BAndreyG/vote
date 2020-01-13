package ru.javawebinar.vote.model;

import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.EnumSet;
import java.util.Set;

@Entity
@Table(name = "users") //,uniqueConstraints = {@UniqueConstraint(columnNames = "name", name = "users_unique_email_idx")})
public class User extends AbstractBaseEntity {

    @NotBlank
    @Size(min = 2, max = 100)
    @Column(name = "name")
    protected String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "email")
    @Email
    @NotBlank
    @Size(max = 100)
    private String email;

    @Column(name="password")
    @Size(min=8,max=250)
    private String password;

    @Column(name = "registered",columnDefinition = "timestamp default now()", insertable = false, updatable =false)
    private Date registered;

    @Column(name = "enabled", nullable = false, columnDefinition = "bool default true")
    private boolean enabled = true;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    @BatchSize(size = 200)
    private Set<Role> roles;

    /*@CollectionTable(name = "votes",joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "registered")
    //@ElementCollection(fetch = FetchType.EAGER)
    @BatchSize(size = 200)
    private Date vote;*/

    @OneToOne(cascade=CascadeType.ALL,fetch = FetchType.EAGER)///optional=false,
   // @CollectionTable(name = "votes",joinColumns = @JoinColumn(name = "user_id"))
    @JoinColumn (name = "id")
    private Vote vote;

    public User(){}

    public User( User u){
        this(u.getId(), u.getName(),u.getEmail(),u.getPassword(), u.getRegistered(), u.isEnabled(), u.getRoles(),u.getVote());
    }

    public User(Integer id,String name,String email, String password,Date registered,Role role,Role... roles){
        this(id,name,email,password,registered,true, EnumSet.of(role, roles), null);
    }

    public User(@Size(min = 8, max = 250) String password, boolean enabled, Set<Role> roles, Vote vote) {
        this.password = password;
        this.registered = new Date();
        this.enabled = enabled;
        this.roles = roles;
        this.vote = vote;
    }

    public User(Integer id, String name, String email,@Size(min = 8, max = 250) String password, @NotNull Date registered, boolean enabled, Set<Role> roles, Vote vote) {
        super(id);
        this.name=name;
        this.email=email;
        this.password = password;
        this.registered = registered;
        this.enabled = enabled;
        this.roles = roles;
        this.vote = vote;
    }
    public User(Integer id, String name, String email,String password, boolean enabled, Set<Role> roles) {
        this.id=id;
        this.name=name;
        this.email=email;
        this.password = password;
        this.enabled = enabled;
        this.roles = roles;
    }

    public User(Integer integer, String name, String toLowerCase, String password, Vote vote, Role roleUser) {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Vote getVote() {
        return vote;
    }

    public void setVote(Vote vote) {
        this.vote = vote;
    }
}
