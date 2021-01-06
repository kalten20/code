package group4.school4you.Resources;

import group4.school4you.Entities.*;
import group4.school4you.Repositories.*;
import group4.school4you.Services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ParentResource {

    private UserService userService;
    private SickNoteRepository sickNoteRepository;
    private StudentRepository studentRepository;
    private ParentRepository parentRepository;
    private AnnouncementRepository announcementRepository;
    private schoolClassRepository schoolClassRepository;
    private Parent parent;


    /* HIER IDEE FÜRS FRONTEND:
    auf einer seite einfach alle kinder anzeigen und dann über button oder wie auch immer die optionen "krankmeldung
    erstellen" und "noten anzeigen" und je nachdem dann passende funktion aufrufen (hier jetzt mit email des studenten -
    genauso gut auch die id möglich, dann müss mas nur kurz hier ändern im code)
     */
    /**
     * To get all kids of a parent.
     * @param familyID the family id is used to find all kids of the family in the database.
     * @return a list of all students in the family.
     */
    @GetMapping(path = "/parent/getKids/{familyID}")
    public List<Student> getKids(@PathVariable long familyID){
        List<Student> kids = null;
        List<User> allStudents = studentRepository.findAll();
        for(int i = 0; i < allStudents.size(); i++){
            Student potentiallyKid = (Student) allStudents.get(i);
            if(potentiallyKid.getFamilyId() == familyID){
                kids.add(potentiallyKid);
            }
        }
        return kids;
    }

    /**
     * This method allows a parent to create a sick note for a student.
     * @param studentMail Required student mail to get the right student from the database.
     * @param sickNote a sick note is created and saved to the database.
     */
    @PostMapping(path = "/parent/sickNote/{EmailStudent}")
    public void createSickNote(@PathVariable String studentMail, @RequestBody SickNote sickNote){
        Student student = (Student) studentRepository.findByEmail(studentMail);
        sickNoteRepository.save(parent.createSickNote(student.getId(), sickNote.getDate(), studentMail,
                sickNote.getContent(), student.getRole()));
    }

    @GetMapping(path = "/parent/studentGrades/{EmailStudent}")
    public List<Grade> getStudentGrades(@PathVariable String studentMail){
        Student student = (Student) studentRepository.findByEmail(studentMail);
        return student.getStudentsGrades();
    }
}
