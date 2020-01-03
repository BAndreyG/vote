package ru.javawebinar.vote.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "restorans")
@ToString(of={"id","registered"})
@EqualsAndHashCode(of={"id"})
public class Restoran extends AbstractNamedEntity{

   /* public static final int START_SEQ = 100000;
    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    private int id;*/

    @Column(name = "name")
    private String name;

    @Column(name = "sum_vote")
    private int sum_vote;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restoran")
    @JsonIgnore
    private List<Menu> menus;


    public Restoran() {
    }

    public Restoran(String name, List<Menu> menus){
        this.name=name;
        this.menus = menus;
        this.sum_vote=0;
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
}
