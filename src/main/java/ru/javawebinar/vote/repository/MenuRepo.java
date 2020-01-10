package ru.javawebinar.vote.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.javawebinar.vote.model.Menu;

import java.util.Set;

@Repository
public interface MenuRepo extends JpaRepository<Menu,Integer> {
    Menu findById(int id);

    @Query("SELECT m FROM Menu m WHERE m.restoran_id=:id")
    public Set<Menu> getMenus(@Param("id")int id);
}
