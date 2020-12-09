package group4.school4you.Resources;

import group4.school4you.Entities.User;
import group4.school4you.Repositories.StudentRepository;
import group4.school4you.Repositories.TeacherRepository;
import group4.school4you.Repositories.UserJpaRepository;
import group4.school4you.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserResource {

    @Autowired
    private UserService userService;

    @Autowired
    private UserJpaRepository userJpaRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TeacherRepository teacherRepository;

    @GetMapping(path = "/students/all")
    public List<User> getAllStudents() {
        //return an Optional object
        List<User> all = studentRepository.findAll();
        return all;
    }

    @GetMapping(path = "/teachers/all")
    public List<User> getAllTeachers() {
        //return an Optional object
        List<User> all = teacherRepository.findAll();
        return all;
    }






}
