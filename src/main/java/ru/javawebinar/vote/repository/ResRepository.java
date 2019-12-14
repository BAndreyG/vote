package ru.javawebinar.vote.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.vote.model.Restoran;

@Transactional(readOnly = true)
public interface ResRepository extends JpaRepository<Restoran, Integer> {

    Restoran findById(int id);
}
