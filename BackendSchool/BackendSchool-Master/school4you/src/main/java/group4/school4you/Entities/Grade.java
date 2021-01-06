package group4.school4you.Entities;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Grade {

    private long id;
    private long studentID;
    private long examID;
    //evtl double aus grade machen? -> 13 punkte oder auch 1,3 möglich
    private int grade;
    //mit type ist schriftlich oder mündlich gemeint
    private String type;

    public Grade(long studentID, long examID, int grade, String type){
        this.studentID = studentID;
        this.examID = examID;
        this.grade = grade;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getStudentID() {
        return studentID;
    }

    public void setStudentID(long studentID) {
        this.studentID = studentID;
    }


    public long getExamID() {
        return examID;
    }

    public void setExamID(long examID) {
        this.examID = examID;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
