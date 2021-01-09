package group4.school4you.Entities;

import group4.school4you.Objects.Subject;
import group4.school4you.Objects.Type;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Appointment {

    @Id
    @GeneratedValue
    private Long id;
    private Long classId;
    private Long teacherId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private String dayName;
    private String slot;
    private Subject subject;
    private Type type;

    public Appointment(){}

    public Appointment(Long classId, Long teacherId, LocalDate date, String slot) {
        this.classId = classId;
        this.teacherId = teacherId;
        this.date = date;
        this.slot = slot;
    }
    public Appointment(Long classId, Long teacherId, LocalDate date,
                       String slot, Subject subject) {
        this.classId = classId;
        this.teacherId = teacherId;
        this.date = date;
        this.slot = slot;
        this.subject = subject;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return teacherId.equals(that.teacherId) &&
                date.equals(that.date) &&
                slot.equals(that.slot);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, classId, teacherId, date, dayName, slot, subject);
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", classId=" + classId +
                ", teacherId=" + teacherId +
                ", date=" + date +
                ", dayName='" + dayName + '\'' +
                ", slot='" + slot + '\'' +
                ", subject='" + subject + '\'' +
                ", type=" + type +
                '}';
    }
}