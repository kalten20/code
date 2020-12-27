package group4.school4you.Repositories;


import group4.school4you.Entities.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {
    @Query("FROM Exam")
    List<Exam> findAll();
}
