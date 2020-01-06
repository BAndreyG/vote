package ru.javawebinar.vote.TO;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class VoteTo extends BaseTo {

    @Column(name = "registered", nullable = false, columnDefinition = "timestamp default now()")
    @NotNull
    private Date registered;

    @NotNull
    private int user_id;

    @NotNull
    private int restoran_id;

    public VoteTo() {
    }

    public VoteTo(int uId, int resId) {
        this.user_id = uId;
        this.restoran_id = resId;
        this.registered = new Date();
    }

    @Override
    public String toString() {
        return "VoteTo{" +
                "registered=" + registered +
                ", user_id=" + user_id +
                ", restoran_id=" + restoran_id +
                ", id=" + id +
                '}';
    }
}
