package ru.javawebinar.vote.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "restorans")
public class Restoran extends AbstractNamedEntity {
    @Column(name = "sum_vote")
    private Integer sum_vote;

    public Restoran() {
    }

    public Restoran(Integer id) {
        this.id = id;
    }

    public Restoran(Integer id, String name) {
        this.id = id;
        this.name = name;
        sum_vote = 0;
    }

    public Integer getSum_vote() {
        return sum_vote;
    }

    public void setSum_vote(Integer sum_vote) {
        this.sum_vote = sum_vote;
    }
}
