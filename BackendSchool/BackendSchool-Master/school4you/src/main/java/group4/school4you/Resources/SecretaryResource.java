package group4.school4you.Resources;

import group4.school4you.Entities.Secretary;
import group4.school4you.Entities.Student;
import group4.school4you.Entities.User;
import group4.school4you.Entities.schoolClass;
import group4.school4you.Repositories.*;
import group4.school4you.Services.SecretaryService;
import group4.school4you.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class SecretaryResource {

    @Autowired
    private UserJpaRepository userRepository;
    private ParentRepository parentRepository;
    private StudentRepository studentRepository;
    private schoolClassRepository schoolClassRepository;

    private Secretary secretary;

    private UserService userService;
    private SecretaryService secretaryService;






    /**
     * This method allows a secretary member to approve an user as a parent or a child in the database.
     * @param id The id to get the belonging user from the database.
     */
    @PutMapping(path = "/secretary/approve/{id}")
    public void approveUser(@PathVariable long id){
        User toEdit = userService.findById(id);
        secretary.setApproved(toEdit);
        userRepository.save(toEdit);
    }

    /**
     * This method allows a secretary member to change the mail adress from the parent or student.
     * @param id The id to identify the belonging user from the database.
     * @param mailAdress The new mail adress the user gets.
     */
    @PutMapping(path = "/secretary/editUser/{id}/{mailAdress}")
    public void editUsersMail(@PathVariable long id, @PathVariable String mailAdress){
        User toEdit = userService.findById(id);
        secretary.changeUsersMailAdress(toEdit, mailAdress);
        userRepository.save(toEdit);
    }

    /**
     * This method allows a secretary member to change the name of a student or parent.
     * @param id The id to identify the belonging user from the database
     * @param firstName The new first name of the user.
     * @param lastName The new last name of the user.
     */
    @PutMapping(path = "/secretary/editUser/{id}/{firstName}/{lastName}")
    public void editUsersName(@PathVariable long id, @PathVariable String firstName, @PathVariable String lastName){
        User toEdit = userService.findById(id);
        secretary.changeUsersName(toEdit, firstName, lastName);
        userRepository.save(toEdit);
    }

    /**
     * This method allows a secretary member to change the class of a student.
     * @param id The id to identify the belonging user from the database
     * @param classID The new class of the student.
     */
    @PutMapping(path = "/secretary/editUser/{id}/{classID}")
    public void editUsersClass(@PathVariable long id, @PathVariable long classID){
        Student toEdit = (Student) userService.findById(id);
        toEdit.setClassId(classID);
        userRepository.save(toEdit);
    }

    /**
     * This method allows a secretary member to delete an user.
     * @param id The id to get the user which is deleted.
     */
    @DeleteMapping(path = "/secretary/deleteUser/{id}" )
    @CrossOrigin(origins = "http://localhost:4200")
    public void deleteUser(@PathVariable long id) {
        userRepository.deleteById(id);
    }

    //OFFEN HALTEN : muss das Sekretariat die familienID ändern können?

    //==========================KLASSEN=================================


    //wollen wir wirklich eine maximale teilnehmeranzahl? kommt in der praxis ja eigentlich nicht vor,
    //dass eine klasse so groß wird?
    //wie handhaben wir es wenn eine klasse mit diesem namen schon existiert?
    /**
     * With this method a secretary user creates a new class. It checks if the class is already existing
     * in the database. If not it creates a new one.
     * @param className The name of the new class.
     * @param maxParticipants Maximum of participants the new class can take.
     */
    @PostMapping(path = "/{className}/{maxParticipants}/neu")
    public void createClass(@PathVariable String className, @PathVariable int maxParticipants) {
        if(secretaryService.findByName(className).equals(null)){
            schoolClass newClass = new schoolClass(className, maxParticipants);
            schoolClassRepository.save(newClass);
        } else {
            System.out.println("This class already exists.");
        }
    }
}
