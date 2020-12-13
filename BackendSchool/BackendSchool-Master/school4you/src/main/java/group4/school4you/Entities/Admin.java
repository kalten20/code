package group4.school4you.Entities;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

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

        List<User> allUsers = userRepository.findAll();
        // list of all secretary members and teachers
        List<User> secretaryTeacher = null;
        //list of all unapproved secretary members and teachers
        List<User> unapproved = null;

        //find all secretary members and teachers from the database
        for(int i = 0; i < allUsers.size(); i++){
            if(allUsers.get(i).getRole().equals("secretary")||allUsers.get(i).getRole().equals("teacher")){
                secretaryTeacher.add(allUsers.get(i));
            }
        }
        //get all unapproved secretary members and teachers
        for(int i = 0; i < allUsers.size(); i++){
            if(!secretaryTeacher.get(i).isApproved){
                unapproved.add(secretaryTeacher.get(i));
            }
        }
        return unapproved;
    }
}
