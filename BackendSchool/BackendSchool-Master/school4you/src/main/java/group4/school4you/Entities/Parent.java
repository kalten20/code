package group4.school4you.Entities;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Parent extends User {

    private Long familyId;
    private Long classId;
    public Parent(){}
    public Parent(String name, String role) {
        super(name,role);
    }


}
