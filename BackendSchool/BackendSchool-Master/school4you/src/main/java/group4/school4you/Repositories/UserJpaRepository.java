package group4.school4you.Repositories;

import group4.school4you.Entities.User;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public interface UserJpaRepository extends JpaRepository<User,Long> {

}
