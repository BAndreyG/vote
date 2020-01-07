package ru.javawebinar.vote.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    @ManyToOne(optional = false,fetch = FetchType.EAGER)
    //@OneToMany(cascade = CascadeType.ALL,mappedBy="menus",fetch = FetchType.LAZY)
   // @CollectionTable(name = "restorans", joinColumns = @JoinColumn(name = "id"))
   // @JoinColumn(name = "restoran_id")//,nullable = false
    //@Column(name = "sum_vote")
    @OnDelete(action = OnDeleteAction.CASCADE)
   // @NotNull
    @JsonIgnore
    private Restoran restoran;

    public Menu(){}

    public Menu(Integer id,String name,Double price){
        super(id, name);
        this.price=price;
        this.enabled=true;
        this.registered=new Date();
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

    public Restoran getRestoran_id() {
        return restoran;
    }

    public void setRestoran_id(Restoran restoran) {
        this.restoran = restoran;
    }
}
