package ru.javawebinar.vote.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.javawebinar.vote.model.Menu;
import ru.javawebinar.vote.model.Restoran;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RestoranRepo extends JpaRepository<Restoran,Integer> {
    Restoran getById(int id);

//    @Query("SELECT m FROM Menu m WHERE m.restoran_id=:id")
//    public Set<Menu> getMenus(@Param("id")int id);
}
