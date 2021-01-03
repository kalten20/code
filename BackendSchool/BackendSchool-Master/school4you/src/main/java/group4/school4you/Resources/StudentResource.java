package group4.school4you.Resources;

import group4.school4you.Entities.Announcement;
import group4.school4you.Entities.Student;
import group4.school4you.Entities.User;
import group4.school4you.Entities.schoolClass;
import group4.school4you.Repositories.StudentRepository;
import group4.school4you.Repositories.schoolClassRepository;
import group4.school4you.Repositories.AnnouncementRepository;
import group4.school4you.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class StudentResource {

    @Autowired
    private StudentRepository studentRepository;
    private UserService userService;
    private schoolClassRepository schoolClassRepository;
    private AnnouncementRepository announcementRepository;


    /**
     * this method gets all announcements for a certain student to show them in an overview. At first it searches for
     * all announcements which are for all students and then it addes the announcements of the students class.
     * @param studentMail the students mail to get the certain student from the database.
     * @return a list of announcements with all announcements for the student to show them in the frontend.
     */
    @GetMapping(path = "/student/getAllAnnouncements/{studentMail}")
    public List<Announcement> studentAnnouncements(@PathVariable String studentMail){
        Student student = (Student) studentRepository.findByEmail(studentMail);
        List<Announcement> allAnnouncements = announcementRepository.findAll();
        List<Announcement> studentAnnouncements = null;

        //generelle Ankündigungen für Schüler werden hinzugefügt
        for(int i = 0; i < allAnnouncements.size(); i++){
            if(allAnnouncements.get(i).getVisibility().contains("student")){
                studentAnnouncements.add(allAnnouncements.get(i));
            }
        }
        //klassenspezifische ankündigungen werden hinzugefügt
        schoolClass studentsClass = schoolClassRepository.getOne(student.getClassId());
        for(int i = 0; i < studentsClass.getClassesAnnouncements().size(); i++){
            studentAnnouncements.add(studentsClass.getClassesAnnouncements().get(i));
        }

        return studentAnnouncements;
    }




}
