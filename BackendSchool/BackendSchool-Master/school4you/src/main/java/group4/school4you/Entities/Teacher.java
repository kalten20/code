package group4.school4you.Entities;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.time.LocalDate;
import java.util.Date;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Teacher extends User {

    public Teacher(){}
    public Teacher (String firstName, String lastName, String email
            ,String password, String role, LocalDate birthDate) {
        super(firstName,lastName,email,password,role,birthDate);
    }

    public Exam createExam(long teacherId, long classId, String subject, Date date, String description){
        return new Exam(teacherId, classId, subject, date, description);
    }

    public void editExam(Exam toEdit, long classId, String subject, Date date, String description){
        toEdit.setClassID(classId);
        toEdit.setSubject(subject);
        toEdit.setDate(date);
        toEdit.setDescription(description);
    }
}
