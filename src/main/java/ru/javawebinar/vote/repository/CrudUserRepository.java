package ru.javawebinar.vote.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javawebinar.vote.model.User;

import java.util.List;

//@Transactional(readOnly = true)
public interface CrudUserRepository extends JpaRepository<User,Integer> {

    public int deleteById(int id);

    public User findById(int id);

    List<User> findAllBy();

    User getByEmail(String email);

    /*@Transactional
    @Modifying
    @Query("DELETE FROM User u WHERE u.id=:id")
    int delete(@Param("id") int id);*/

}
