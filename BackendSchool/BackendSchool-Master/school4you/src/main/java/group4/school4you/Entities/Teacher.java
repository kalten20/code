package group4.school4you.Entities;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

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

    public SickNote createSickNote(long userId, Date date, String email, String content, String role){
        SickNote sickNote = new SickNote(userId, date, email,content, role);
        return sickNote;
    }

    public Announcement createAnnouncement(String sender, List<String> visibility, String subject,
                                           String content, Date date, long classId){
        Announcement created = new Announcement(sender, visibility, subject, content, date, classId);
        return created;
    }

    public void addStudentGrade(Student student, Grade grade){
        student.addGrade(grade);
    }

    public void removeStudentGrade(Student student, Grade grade){

    }
}
