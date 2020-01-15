package ru.javawebinar.vote.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "restorans")
public class Restoran extends AbstractBaseEntity {

    @Override
    public String toString() {
        return "Restoran{" +
                "name='" + name + '\'' +
                ", sum_vote=" + sum_vote +
                ", id=" + id +
                '}';
    }

    @NotBlank
    @Size(min = 2, max = 100)
    @Column(name = "name")
    private String name;

    @Column(name = "sum_vote")
    private int sum_vote;

    public Restoran() {
    }

    public Restoran(String name) {
        this.name = name;
        this.sum_vote = 0;
    }

    public Restoran(Integer id, String name, int sum_vote) {
        this.id = id;
        this.name = name;
        this.sum_vote = sum_vote;
    }

    public Restoran(Restoran restoran) {
        this(restoran.getId(), restoran.getName(), 0);
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
}
