package ru.stc21.internal;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCrudRepository extends JpaRepository<User, String> {

    User findByLogin(String login);
    User findByLoginLike(String login);

    @Query("select u from User u where u.date > :date")
    List<User> getUsersByLoginLike(@Param("date") String date);

    @Modifying
    @Query("update User u set u.login = ?1 where u.login = ?2")
    int updateLogin(String newLogin, String oldLogin);

    List<User> fetchByLoginLength(@Param("length") long length);

}
