package ru.javawebinar.vote.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.javawebinar.vote.model.Restoran;

import javax.lang.model.element.Name;
import java.util.List;

@Repository
public class RestoranRepository {

    @Autowired
    ResRepository repository;

    public List<Restoran> getAll(){return repository.findAll(Sort.by(Restoran.class.getName()));}

    public Restoran get(int id) {
        return repository.findById(id);
    }

    public Restoran create(Restoran restoran) {
        return repository.save(restoran);
    }
}
