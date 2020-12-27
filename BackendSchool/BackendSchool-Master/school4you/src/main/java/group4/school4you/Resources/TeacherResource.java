package group4.school4you.Resources;


import group4.school4you.Entities.Teacher;
import group4.school4you.Entities.schoolClass;
import group4.school4you.Repositories.AnnouncementRepository;
import group4.school4you.Repositories.TeacherRepository;
import group4.school4you.Repositories.schoolClassRepository;
import group4.school4you.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TeacherResource {
    @Autowired
    private TeacherRepository teacherRepository;
    private AnnouncementRepository announcementRepository;
    private schoolClassRepository schoolClassRepository;
    private UserService userService;

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
}
