package group4.school4you.Resources;

import group4.school4you.Entities.*;
import group4.school4you.Repositories.*;
import group4.school4you.Services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
}
