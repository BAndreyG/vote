package ru.javawebinar.vote.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javawebinar.vote.model.User;

//@Transactional(readOnly = true)
public interface CrudUserRepository extends JpaRepository<User,Integer> {

    public int deleteById(int id);

    public User findById(int id);

    /*@Transactional
    @Modifying
    @Query("DELETE FROM User u WHERE u.id=:id")
    int delete(@Param("id") int id);*/

}
