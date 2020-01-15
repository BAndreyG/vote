package ru.javawebinar.vote.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.vote.model.Menu;
import ru.javawebinar.vote.model.Restoran;

import java.util.Set;

@Repository
@Transactional(readOnly = true)
public interface RestoranRepo extends JpaRepository<Restoran, Integer> {

    Restoran findById(int id);

    @Query("SELECT m FROM Menu m WHERE m.restoran_id=:id AND m.enabled=true")
    Set<Menu> getMenu(@Param("id") int id);

    @Modifying
    @Transactional
    @Query("UPDATE Restoran set sum_vote=sum_vote+1 WHERE id=:id ")
    void sumVoteIncrement(int id);

    @Modifying
    @Transactional
    @Query("UPDATE Restoran set sum_vote=sum_vote-1 WHERE id=:id ")
    void sumVoteDecrement(int id);

}
