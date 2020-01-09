package ru.javawebinar.vote.repository;

import org.springframework.data.jpa.repository.Query;
import ru.javawebinar.vote.model.Restoran;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestoranRepo extends JpaRepository<Restoran,Integer> {
    Restoran getById(int id);
}
