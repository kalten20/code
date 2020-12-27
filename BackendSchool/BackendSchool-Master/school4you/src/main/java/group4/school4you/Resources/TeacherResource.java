package group4.school4you.Resources;


import group4.school4you.Entities.Exam;
import group4.school4you.Entities.Teacher;
import group4.school4you.Entities.User;
import group4.school4you.Entities.schoolClass;
import group4.school4you.Repositories.*;
import group4.school4you.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TeacherResource {
    @Autowired
    private TeacherRepository teacherRepository;
    private UserJpaRepository userRepository;
    private AnnouncementRepository announcementRepository;
    private schoolClassRepository schoolClassRepository;
    private ExamRepository examRepository;
    private UserService userService;
    private Teacher teacherToWorkWith;



    /**
     * This methods gets an id from the frontend of a certain teacher and searches in the database for all classes
     * which contain this teacher in its list of teachers. Then it collects all this classes to give a list of all
     * classes teached by this teacher back to the frontend.
     * @param id To get the teacher from the repository.
     * @return A list of all classes which the teachers teaches.
     */
    @GetMapping(path = "/teacher/classes/{id}")
    public List<schoolClass> getClasses(@PathVariable long id){
        List<schoolClass> allClasses = schoolClassRepository.findAll();
        List<schoolClass> toReturn = null;
        Teacher toGetClasses = (Teacher) teacherRepository.findById(id).get();

        for(int i = 0; i < allClasses.size(); i++){
            if(allClasses.get(i).containsTeacher(toGetClasses)) {
                toReturn.add(allClasses.get(i));
            }
        }
        return toReturn;
    }


    /**
     * A teacher can create a new exam for a certain class on a certain date. For this the method gets all the infos
     * from the frontend and creates a exam with the teacherId, a classId(which class will write the exam), a subject -
     * in which subject the exam will be, a date when the exam will be and a short description of the exam.
     * @param id The teacher id to create an exam with this id.
     * @param exam All the requested data from the frontend to create a new exam in the backend.ja
     */
    @PostMapping(path = "/teacher/exam/neu/{teacherId}")
    public void createExam(@PathVariable long id, @RequestBody Exam exam){
         Teacher teacher = (Teacher) userService.findById(id);
         examRepository.save(teacher.createExam(id, exam.getClassID(), exam.getSubject(),
                 exam.getDate(), exam.getDescription()));
    }

    /**
     * A teacher can edit an exam. He can change the class, the subject, the date or the description.
     * @param examId The exam which is changed in the database.
     * @param exam The data for changing the certain exam.
     */
    @PutMapping(path = "/teacher/exam/{examId}")
    public void editExam(@PathVariable long examId, @RequestBody Exam exam){
        Exam toEdit = examRepository.findById(examId).get();
        teacherToWorkWith.editExam(toEdit, exam.getClassID(), exam.getSubject(), exam.getDate(), exam.getDescription());
        examRepository.save(toEdit);
    }
}
