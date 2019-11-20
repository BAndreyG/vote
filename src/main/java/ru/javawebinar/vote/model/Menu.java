package ru.javawebinar.vote.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "menus")
public class Menu extends AbstractNamedEntity {
    @Column(name = "price", nullable = false)
    @NotNull
    private Integer price;

    @Column(name = "enabled", nullable = false, columnDefinition = "bool default true")
    private boolean enabled = true;

    @Column(name = "registered",nullable = false,columnDefinition = "timestamp default now()")
    @NotNull
    private Date registered;

    @CollectionTable(name = "restorans", joinColumns = @JoinColumn(name = "restoran_id"))
    @Column(name = "restoran_id")
    @ElementCollection(fetch = FetchType.EAGER)
    private Integer restoran_id;
}
