package group4.school4you.Entities;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Secretary extends User {

    public Secretary(){}
    public Secretary (String firstName, String lastName, String email
            ,String password, String role, LocalDate birthDate) {
        super(firstName,lastName,email,password,role,birthDate);
    }


    public void changeUsersMailAdress(User user, String mailAdress){
        user.setEmail(mailAdress);
    }

    public void changeUsersName(User user, String firstName, String lastName){
        user.setFirstName(firstName);
        user.setLastName(lastName);
    }

    public void setApproved(User user) { user.setApproved(true);}

    public schoolClass createNewClass(String className, int maxParticipants){
        schoolClass toCreate = new schoolClass(className, maxParticipants);
        return toCreate;
    }

    public void changeClassName(schoolClass classToChangeName, String newName){
        classToChangeName.setClassName(newName);
    }

    public void changeMaxParticipants(schoolClass classToChange, int newMaxParticipants){
        classToChange.setMaxParticipants(newMaxParticipants);
    }

    public void addStudentToClass(schoolClass schoolClass, Student toAdd){
        if(!schoolClass.containsStudent(toAdd)) {
            schoolClass.addStudent(toAdd);
        }
    }

    public void removeStudentFromClass(schoolClass schoolClass, Student toRemove){
        if(schoolClass.containsStudent(toRemove)){
            schoolClass.removeStudent(toRemove);
        }
    }

    public void addTeacherToClass(schoolClass schoolClass, Teacher toAdd){
        if(!schoolClass.containsTeacher(toAdd)){
            schoolClass.addTeacher(toAdd);
        }
    }

    public void removeTeacherFromClass(schoolClass schoolClass, Teacher toRemove){
        if(schoolClass.containsTeacher(toRemove)){
            schoolClass.removeTeacher(toRemove);
        }
    }

    public Announcement createAnnouncement(long id, String sender, List<String> visibility, String subject,
                                           String content, Date date){
        Announcement created = new Announcement(id, sender, visibility, subject, content, date);
        return created;
    }

    public void editAnnouncement(Announcement toEdit, long id, String sender, List<String> visibility, String subject,
                                 String content, Date date){
        toEdit.setSender(sender);
        toEdit.setVisibility(visibility);
        toEdit.setSubject(subject);
        toEdit.setContent(content);
        toEdit.setDate(date);
    }
}
