package ru.javawebinar.vote.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.javawebinar.vote.model.Menu;
import ru.javawebinar.vote.model.Restoran;

import java.util.Set;

@Repository
public interface RestoranRepo extends JpaRepository<Restoran, Integer> {

    boolean deleteById(int id);

    @Query("SELECT m FROM Menu m WHERE m.restoran_id=:id AND m.enabled=true")
    Set<Menu> getMenu(@Param("id") int id);

    @Query("UPDATE Restoran set sum_vote=sum_vote+1 WHERE id=:id ")
    void sumVoteIncrement(int id);

    @Query("UPDATE Restoran set sum_vote=sum_vote-1 WHERE id=:id ")
    void sumVoteDecrement(int id);

}
