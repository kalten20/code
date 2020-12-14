package group4.school4you.Entities;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.time.LocalDate;
import java.util.List;


import group4.school4you.Repositories.SecretaryRepository;
import group4.school4you.Repositories.TeacherRepository;
import group4.school4you.Repositories.UserJpaRepository;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Admin extends User {

    public Admin(){}
    public Admin (String firstName, String lastName, String email
            ,String password, String role, LocalDate birthDate) {
        super(firstName,lastName,email,password,role,birthDate);
    }
    private UserJpaRepository userRepository;
    private TeacherRepository teacherRepository;
    private SecretaryRepository secretaryRepository;

    public void setApproved(User user) { user.isApproved = true;}

    public void changeUsersMailAdress(User user, String mailAdress){
        user.setEmail(mailAdress);
    }

    public void changeUsersName(User user, String firstName, String lastName){
        user.setFirstName(firstName);
        user.setLastName(lastName);
    }

    /**
     * This method is for the frontend to find all unapproved users with the roles secretary&teacher.
     * Once you got them, they can be shown in the frontend.
     * @return a list of all unapproved secretary members and teachers.
     */
    public List<User> getAllUnapproved(){

        //list of all unapproved secretary members and teachers
        List<User> unapproved = null;
        //get all unapproved secretary from the help method "getUnapprovedSecretary"
        List<User> unapprovedSecretary = getUnapprovedSecretary();
        //get all unapproved teachers from the help method "getUnapprovedTeachers"
        List<User> unapprovedTeachers = getUnapprovedTeachers();

        //merge all unapproved parents&students into the list "unapproved"
        for(int i = 0; i < unapprovedSecretary.size(); i++){
            unapproved.add(unapprovedSecretary.get(i));
        }
        for(int i = 0; i < unapprovedTeachers.size(); i++){
            unapproved.add(unapprovedTeachers.get(i));
        }
        return unapproved;
    }

    /**
     * This method finds all unapproved teachers from the database.
     * @return a list of all unapproved teachers.
     */
    public List<User> getUnapprovedTeachers(){
        List<User> teachers = teacherRepository.findAll();
        List<User> unapprovedTeachers = null;

        for(int i = 0; i < teachers.size(); i++){
            if(!teachers.get(i).isApproved){
                unapprovedTeachers.add(teachers.get(i));
            }
        }
        return unapprovedTeachers;
    }

    /**
     * This method finds all unapproved secretary members from the database.
     * @return a list of all unapproved secretary members.
     */
    public List<User> getUnapprovedSecretary(){
        List<User> secretary = secretaryRepository.findAll();
        List<User> unapprovedSecretary = null;

        for (int i = 0; i < secretary.size(); i++){
            if(!secretary.get(i).isApproved){
                unapprovedSecretary.add(secretary.get(i));
            }
        }
        return unapprovedSecretary;
    }
}
