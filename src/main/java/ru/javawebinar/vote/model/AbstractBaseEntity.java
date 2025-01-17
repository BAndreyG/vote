package ru.javawebinar.vote.model;

import org.hibernate.Hibernate;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;


@MappedSuperclass
// http://stackoverflow.com/questions/594597/hibernate-annotations-which-is-better-field-or-property-access
@Access(AccessType.FIELD)
public abstract class AbstractBaseEntity implements Persistable<Integer> {
    public static final int START_SEQ = 100000;
    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    protected Integer id;

    protected AbstractBaseEntity() {
    }

    protected AbstractBaseEntity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isNew() {
        return this.id == null;
    }

    @Override
    public int hashCode() {
        return id == null ? 0 : id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !getClass().equals(Hibernate.getClass(obj))) {
            return false;
        }
        AbstractBaseEntity that = (AbstractBaseEntity) obj;
        return id != null && id.equals(that.id);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "-" + id;
    }
}
