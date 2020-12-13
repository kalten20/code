package group4.school4you.Entities;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.time.LocalDate;
import java.util.List;
import group4.school4you.Repositories.UserJpaRepository;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Secretary extends User {

    public Secretary(){}
    public Secretary (String firstName, String lastName, String email
            ,String password, String role, LocalDate birthDate) {
        super(firstName,lastName,email,password,role,birthDate);
    }
    private UserJpaRepository userRepository;

    public void setApproved(User user) { user.isApproved = true;}

    /**
     * This method is for the frontend to find all unapproved users with the roles parents&students.
     * Once you got them, they can be shown in the frontend.
     * @return a list of all unapproved students and parents.
     */
    public List<User> getAllUnapproved(){

        List<User> allUsers = userRepository.findAll();
        // list of all parents and students
        List<User> studentsParents = null;
        //list of all unapproved parents and students
        List<User> unapproved = null;

        //find all parents and students from the database
        for(int i = 0; i < allUsers.size(); i++){
            if(allUsers.get(i).getRole().equals("secretary")||allUsers.get(i).getRole().equals("teacher")){
                studentsParents.add(allUsers.get(i));
            }
        }
        //get all unapproved secretary members and teachers
        for(int i = 0; i < allUsers.size(); i++){
            if(!studentsParents.get(i).isApproved){
                unapproved.add(studentsParents.get(i));
            }
        }
        return unapproved;
    }

}
