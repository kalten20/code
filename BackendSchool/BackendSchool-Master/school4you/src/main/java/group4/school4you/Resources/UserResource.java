package group4.school4you.Resources;

import group4.school4you.Entities.Admin;
import group4.school4you.Entities.Secretary;
import group4.school4you.Entities.Student;
import group4.school4you.Entities.User;
import group4.school4you.Repositories.*;
import group4.school4you.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private SecretaryRepository sekretaryRepository;

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


    @PostMapping(path = "/{role}/neu")
    public Student createUser(@PathVariable String role,
                                     @RequestBody Student student) {

        return studentRepository.save(student);

//        switch (role) {
//            case "admin" : adminRepository.save(user);
//            break;
//            case "secretary" : sekretaryRepository.save(user);
//            break;
//            case "teacher" : teacherRepository.save(user);
//            break;
//            case "student" : studentRepository.save(user);
//            break;
//            default : return null;
//        } return null;

    }







}
