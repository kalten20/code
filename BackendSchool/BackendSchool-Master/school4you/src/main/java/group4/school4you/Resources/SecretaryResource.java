package group4.school4you.Resources;

import group4.school4you.Entities.Secretary;
import group4.school4you.Entities.Student;
import group4.school4you.Entities.User;
import group4.school4you.Repositories.ParentRepository;
import group4.school4you.Repositories.SecretaryRepository;
import group4.school4you.Repositories.StudentRepository;
import group4.school4you.Repositories.UserJpaRepository;
import group4.school4you.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class SecretaryResource {

    @Autowired
    private UserJpaRepository userRepository;
    private UserService userService;
    private ParentRepository parentRepository;
    private StudentRepository studentRepository;
    private Secretary secretary;


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

    //OFFEN HALTEN : muss das Sekretariat die familienID ändern können? 

}
