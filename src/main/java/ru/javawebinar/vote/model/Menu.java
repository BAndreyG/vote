package ru.javawebinar.vote.model;


import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "menus")
public class Menu extends AbstractNamedEntity {

    @Column(name = "price", nullable = false)
    @NotNull
    private Double price;

    @Column(name = "enabled", nullable = false, columnDefinition = "bool default true")
    private boolean enabled = true;

    @Column(name = "registered",nullable = false,columnDefinition = "timestamp default now()")
    @NotNull
    private Date registered;

    @CollectionTable(name = "restorans", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "name")
    //@OneToMany(fetch = FetchType.EAGER)
    private String restoran;

    public Menu(){}


    public Menu(Integer id,String name,Double price,String restoran){
        super(id, name);/*
        this.id=id;
        this.name=name;*/
        this.price=price;
        this.enabled=true;
        this.registered=new Date();
        this.restoran=restoran;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    public String getRestoran_id() {
        return restoran;
    }

    public void setRestoran_id(String restoran_id) {
        this.restoran = restoran_id;
    }
}
