package group4.school4you.Entities;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.time.LocalDate;
import java.util.List;

import group4.school4you.Repositories.ParentRepository;
import group4.school4you.Repositories.StudentRepository;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Secretary extends User {

    public Secretary(){}
    public Secretary (String firstName, String lastName, String email
            ,String password, String role, LocalDate birthDate) {
        super(firstName,lastName,email,password,role,birthDate);
    }
    private StudentRepository studentRepository;
    private ParentRepository parentRepository;

    public void setApproved(User user) { user.isApproved = true;}

    /**
     * This method is for the frontend to find all unapproved users with the roles parents&students.
     * Once you got them, they can be shown in the frontend.
     * @return a list of all unapproved students and parents.
     */
    public List<User> getAllUnapproved(){

        //list of all unapproved parents and students
        List<User> unapproved = null;
        //get all unapproved parents from the help method "getUnapprovedParents"
        List<User> unapprovedParents = getUnapprovedParents();
        //get all unapproved students from the help method "getUnapprovedStudents"
        List<User> unapprovedStudents = getUnapprovedStudents();

        //merge all unapproved parents&students into the list "unapproved"
        for(int i = 0; i < unapprovedParents.size(); i++){
            unapproved.add(unapprovedParents.get(i));
        }
        for(int i = 0; i < unapprovedStudents.size(); i++){
            unapproved.add(unapprovedStudents.get(i));
        }
        return unapproved;
    }

    /**
     * This method finds all unapproved students from the database.
     * @return a list of all unapproved students.
     */
    public List<User> getUnapprovedStudents(){
        List<User> students = studentRepository.findAll();
        List<User> unapprovedStudents = null;

        for(int i = 0; i < students.size(); i++){
            if(!students.get(i).isApproved){
                unapprovedStudents.add(students.get(i));
            }
        }
        return unapprovedStudents;
    }

    /**
     * This method finds all unapproved parents from the database.
     * @return a list of all unapproved parents.
     */
    public List<User> getUnapprovedParents(){
        List<User> parents = parentRepository.findAll();
        List<User> unapprovedParents = null;

        for (int i = 0; i < parents.size(); i++){
            if(!parents.get(i).isApproved){
                unapprovedParents.add(parents.get(i));
            }
        }
        return unapprovedParents;
    }
}
