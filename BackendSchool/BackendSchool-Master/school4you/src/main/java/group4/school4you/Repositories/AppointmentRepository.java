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

    Appointment findByClassIdAndTeacherIdAndDateAndSlot(Long classId,
                                                        Long teacherId,
                                                        LocalDate date,
                                                        String slot);





}
