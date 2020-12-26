package group4.school4you.Repositories;

import group4.school4you.Entities.User;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Primary
public interface UserJpaRepository extends JpaRepository<User,Long> {

    //User findByEmailAndPassword (String Email,String password);
    User findByEmail(String Email);

    List<User> findAll();

}
