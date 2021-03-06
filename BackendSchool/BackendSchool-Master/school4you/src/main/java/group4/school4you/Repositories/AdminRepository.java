package group4.school4you.Repositories;

import group4.school4you.Entities.User;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface AdminRepository extends UserJpaRepository {
    @Override
    @Query("FROM Admin ")
    List<User> findAll();
}
