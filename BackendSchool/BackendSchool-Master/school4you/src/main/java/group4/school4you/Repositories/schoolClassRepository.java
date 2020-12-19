package group4.school4you.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import group4.school4you.Entities.schoolClass;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface schoolClassRepository extends JpaRepository<schoolClass, Long> {
    @Query("FROM schoolClass ")
    List<schoolClass> findAll();
}
