package ru.javawebinar.vote.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    @ElementCollection(fetch = FetchType.EAGER)
    private Integer restoran_id;

    public Menu(){}

    public Menu(Integer id,String name,Double price,Integer restoran_id){
        this.id=id;
        this.name=name;
        this.price=price;
        this.enabled=true;
        this.registered=new Date();
        this.restoran_id=restoran_id;
    }
}
