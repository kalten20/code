package group4.school4you.Repositories;

import group4.school4you.Entities.Announcement;
import group4.school4you.Entities.schoolClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
    @Query("FROM Announcement ")
    List<Announcement> findAll();
}
