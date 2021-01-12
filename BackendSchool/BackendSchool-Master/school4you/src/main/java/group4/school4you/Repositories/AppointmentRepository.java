package group4.school4you.Repositories;

import group4.school4you.Entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentRepository extends JpaRepository <Appointment,
        Long> {
    List<Appointment> findAllByTeacherIdAndDateAndSlotAndClassIdNot(Long teacherId,
                                                                    LocalDate date,
                                                                    String slot,
                                                                    Long classId);
    boolean existsByTeacherIdAndDateAndSlotAndClassId (
            Long teacherId,
            LocalDate date,
            String slot,
            Long classId
    );

    boolean existsByClassIdAndDateAndSlot(Long classId, LocalDate date,
                                          String slot);

    Appointment findByClassIdAndTeacherIdAndDateAndSlot(Long classId,
                                                        Long teacherId,
                                                        LocalDate date,
                                                        String slot);
    Appointment findByClassIdAndDateAndSlot (Long classId,
                                             LocalDate date, String slot);

    Appointment findByTeacherIdAndDateAndSlot (Long teacherId,
                                             LocalDate date, String slot);

    //Finds when teachers of this class have appointments in other classes
    List<Appointment> findAllByDateAndSlotAndClassIdNotAndTeacherIdIn(
            LocalDate date, String slot, Long classId, List<Long> teacherIds
    );

    //List<Appointment> findAllByTeacherIdAndDateIs

    void deleteAllByRecurrenceIdAndDateIsGreaterThanEqual(Long recurrenceId, LocalDate date);





}
