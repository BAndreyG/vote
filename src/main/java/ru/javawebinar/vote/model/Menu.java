package ru.javawebinar.vote.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "menus")
public class Menu extends AbstractBaseEntity {

    @NotBlank
    @Size(min = 2, max = 100)
    @Column(name = "name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "price", nullable = false)
    @NotNull
    private Double price;

    @Column(name = "enabled", nullable = false, columnDefinition = "bool default true")
    private boolean enabled = true;

    @Column(name = "registered",nullable = false,columnDefinition = "timestamp default now()")
    @NotNull
    private Date registered;

    private int restoran_id;

    public Menu(){}

    public Menu(Integer id,String name,Double price){
        super(id);
        this.name=name;
        this.price=price;
        this.enabled=true;
        this.registered=new Date();
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    public int getRestoran_id() {
        return restoran_id;
    }

    public void setRestoran_id(int restoran) {
        this.restoran_id = restoran;
    }
}
