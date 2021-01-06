package group4.school4you.Entities;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.time.LocalDate;
import java.util.List;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Student extends User {

    private Long familyId;
    private Long classId;
    private List<Grade> studentsGrades = null;

    public Student(){}
    public Student(String firstName, String lastName, String email
            ,String password, String role, LocalDate birthDate) {
        super(firstName,lastName,email,password,role,birthDate);
    }


    public List<Grade> getStudentsGrades() {
        return studentsGrades;
    }

    public void setStudentsGrades(List<Grade> studentsGrades) {
        this.studentsGrades = studentsGrades;
    }

    public void addGrade(Grade gradeToAdd){
        this.studentsGrades.add(gradeToAdd);
    }

    public void removeGrade(Grade gradeToRemove){
        this.studentsGrades.remove(gradeToRemove);
    }

    public Long getFamilyId() {
        return familyId;
    }

    public void setFamilyId(Long familyId) {
        this.familyId = familyId;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }
}
