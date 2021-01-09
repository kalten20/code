package group4.school4you.Resources;

import group4.school4you.Entities.Appointment;
import group4.school4you.Objects.FieldStatus;
import group4.school4you.Services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDate;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AppointmentResource {

    @Autowired
    AppointmentService appointmentService;

    @PostMapping (path = "appointment/create")
    public Appointment createAppointment(@RequestBody Appointment
                                                     newAppointment) {
        return appointmentService.createAppointment(newAppointment);
    }

    @GetMapping (path = "/appointment/{id}")
    public Appointment getAppointmentById(@PathVariable Long id) {
        return appointmentService.findAppointmentById(id);
    }

    @GetMapping (path = "/appointment/{classId}/{teacherId}/{date}/{slot}")
    public Appointment
    ByClassIsAndTeacherIdAndDateAndSlot(@PathVariable Long classId,
                                        @PathVariable Long teacherId,
                                        @PathVariable String date,
                                        @PathVariable String slot){
        LocalDate appointmentDate = LocalDate.parse(date);
        return appointmentService.findAppointmentBy(classId,teacherId,
                appointmentDate,slot);
    }

    @Transactional
    @PutMapping (path = "/appointment/{id}/edit/{newSubjectString}")
    public Appointment editAppointmentSubjectById(@PathVariable Long id,
                                                  @PathVariable String newSubjectString) {
        return appointmentService.editAppointment(id,newSubjectString);
    }

    @DeleteMapping (path = "/appointment/{id}/delete")
    public ResponseEntity<String> deleteAppointmentById(@PathVariable Long id) {
        return appointmentService.deleteAppointmentById(id);
    }

    //RETURN TRUE IF APPOINTMENT IS AVAILABLE TO SET FOR THE TEACHER IN THAT
    // CLASS OR EXISTS IN THAT SPECIFIC CLASS
    //WILL RETURN FALSE IF THE APPOINTMENT IS BOOKED BY ANOTHER CLASS
    @PutMapping(path = "/appointment/availability")
    public boolean checkAvailability(@RequestBody Appointment appointment) {
        System.out.println(appointment);

        return appointmentService.checkAvailability(appointment);
    }

    //gets the status of the represented field in the timetable
    @PutMapping (path = ("/appointment/fieldStatus"))
    public FieldStatus getFieldStatus(@RequestBody Appointment appointment) {
        return appointmentService.getFieldStatus(appointment);
    }














}
