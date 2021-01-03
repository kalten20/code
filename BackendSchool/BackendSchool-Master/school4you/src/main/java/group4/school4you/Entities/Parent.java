package group4.school4you.Entities;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.util.Date;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Parent extends User {

    private Long familyId;

    public Long getFamilyId() {
        return familyId;
    }

    public void setFamilyId(Long familyId) {
        this.familyId = familyId;
    }

    public Parent(){}
    public Parent(String name, String role) {
        super(name,role);
    }

    public SickNote createSickNote(long userId, Date date, String email, String content, String role){
        SickNote sickNote = new SickNote(userId, date, email, content, role);
        return sickNote;
    }
}
