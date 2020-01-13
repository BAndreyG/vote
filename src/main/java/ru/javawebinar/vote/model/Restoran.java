package ru.javawebinar.vote.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "restorans")
@ToString(of={"id","registered"})
@EqualsAndHashCode(of={"id"})
public class Restoran extends AbstractBaseEntity{

    @NotBlank
    @Size(min = 2, max = 100)
    @Column(name = "name")
    private String name;

    @Column(name = "sum_vote")
    private int sum_vote;

    /*@OneToMany(fetch = FetchType.LAZY)
    @JsonIgnore*/
    //private Set<Menu> menus;


    public Restoran() {
    }

 /*   public Restoran(String name, Set<Menu> menus){
        this.name=name;
        this.menus = menus;
        this.sum_vote=0;
    }*/

    public Restoran(String name){
        this.name=name;
        this.sum_vote=0;
    }

    public Restoran(Integer id, String name, int sum_vote) {//, Set<Menu> menus
        this.id=id;
        this.name=name;
       // this.menus=menus;
        this.sum_vote=sum_vote;
    }

    /*public Set<Menu> getMenus() {
        return menus;
    }

    public void setMenus(Set<Menu> menus) {
        this.menus = menus;
    }*/

    public Restoran(Restoran restoran) {
        this(restoran.getId(),restoran.getName(),0);
    }//restoran.getMenus(),

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSum_vote() {
        return sum_vote;
    }

    public void setSum_vote(int sum_vote) {
        this.sum_vote = sum_vote;
    }
}
