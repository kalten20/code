package group4.school4you.Entities;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Teacher extends User {

    @ManyToMany
    private List<SchoolClass> classes;

    public Teacher(){}
    public Teacher (String firstName, String lastName, String email
            ,String password, String role, LocalDate birthDate) {
        super(firstName,lastName,email,password,role,birthDate);
        this.classes = new ArrayList<>();
    }
}
