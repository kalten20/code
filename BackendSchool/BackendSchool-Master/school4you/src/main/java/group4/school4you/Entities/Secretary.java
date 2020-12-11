package group4.school4you.Entities;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.time.LocalDate;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Secretary extends User {

    public Secretary(){}
    public Secretary (String firstName, String lastName, String email
            ,String password, String role, LocalDate birthDate) {
        super(firstName,lastName,email,password,role,birthDate);
    }

    public void setApproved(User user) { user.isApproved = true;}
}
