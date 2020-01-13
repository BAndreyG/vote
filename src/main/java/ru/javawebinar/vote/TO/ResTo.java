package ru.javawebinar.vote.TO;

import ru.javawebinar.vote.model.Menu;
import ru.javawebinar.vote.model.Restoran;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

public class ResTo  extends BaseTo{

    @NotBlank
    @Size(min = 2, max = 100)
    private String name;

    private int sum_vote;

    //private Set<Menu> menus;

    public ResTo() {
    }

    public ResTo(Restoran restoran) {
        this(restoran.getId(),restoran.getName(),restoran.getSum_vote()); //restoran.getMenus()
    }

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

   /* public Set<Menu> getMenus() {
        return menus;
    }

    public void setMenus(Set<Menu> menus) {
        this.menus = menus;
    }*/

    public ResTo(Integer id, String name, int sum_vote) {// , Set<Menu> menus
        this.id=id;
        this.name=name;
       // this.menus=menus;
        this.sum_vote=sum_vote;
    }

    @Override
    public String toString() {
        return "ResTo{" +
                "name='" + name + '\'' +
                ", sum_vote=" + sum_vote +
               // ", menus=" + menus +
                ", id=" + id +
                '}';
    }
}
