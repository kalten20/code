package group4.school4you.Repositories;

import group4.school4you.Entities.SickNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SickNoteRepository extends JpaRepository<SickNote,Long> {

}
