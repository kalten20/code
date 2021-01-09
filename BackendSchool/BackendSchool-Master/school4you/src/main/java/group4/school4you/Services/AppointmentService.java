package group4.school4you.Services;

import group4.school4you.Entities.Appointment;
import group4.school4you.Objects.FieldStatus;
import group4.school4you.Objects.Subject;
import group4.school4you.Repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    AppointmentRepository appointmentRepository;

    public boolean checkAvailability(Appointment appointment) {
        List<Appointment> unavailableList = appointmentRepository.findAllByTeacherIdAndDateAndSlotAndClassIdNot(
                appointment.getTeacherId(),appointment.getDate(),
                appointment.getSlot(),appointment.getClassId());
        return !unavailableList.contains(appointment);
    }

    public FieldStatus getFieldStatus(Appointment appointment) {
        boolean available = checkAvailability(appointment);
        if (! available) {
            return FieldStatus.UNAVAILABLE;
        } else if (currentFieldIsSet(appointment)) {
            return FieldStatus.EDIT;
        } else if (! currentFieldIsSet(appointment)) {
            return FieldStatus.CREATE;
        }
        else return FieldStatus.ERROR;
    }

    private boolean currentFieldIsSet(Appointment appointment) {
        return appointmentRepository.existsByTeacherIdAndDateAndSlotAndClassId(
                appointment.getTeacherId(),appointment.getDate(),
                appointment.getSlot(),appointment.getClassId()
        );
    }

    public Appointment createAppointment(Appointment newAppointment) {
        return appointmentRepository.save(newAppointment);
    }

    public Appointment findAppointmentById(Long id) {
        return appointmentRepository.findById(id).get();
    }


    public Appointment editAppointment(Long id, String newSubjectString) {
        Appointment toEdit = findAppointmentById(id);
        //TRY CONVERTING SUBJECTSTRING TO ENUM IF NOT POSSIBLE
        // EXCEPTION WRONG ARMUMENTS
        //TRY CATCH
        Subject newSubject = Subject.valueOf(newSubjectString);
        toEdit.setSubject(newSubject);
        return toEdit;
    }

    public ResponseEntity<String> deleteAppointmentById(Long id) {
        if (appointmentRepository.existsById(id)) {
            appointmentRepository.deleteById(id);
            return new ResponseEntity<>("Deleted Appointment with id :  "
            + id , HttpStatus.OK);
        } else {
            //NO SUCH ELEMENT EXCEPTION try catch
            return new ResponseEntity<>("No such element",
                    HttpStatus.BAD_REQUEST);
        }
    }

    public Appointment findAppointmentBy(Long classId, Long teacherId,
                                         LocalDate date, String slot) {
        return appointmentRepository
                .findByClassIdAndTeacherIdAndDateAndSlot(classId, teacherId,
                        date,slot
                );
    }
}
