package group4.school4you.Entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class schoolClass {
    @Id
    @GeneratedValue
    private int id;
        private String className;
        private int maxParticipants;

        @OneToMany
        private List<Student> students;
        @OneToMany
        private List<Teacher> teachers;

        //eine klasse bekommt noch eine liste von ankündigungen -> so kann für bestimmte klassen eine ankündigung
        //erstellt werden

        private List<Announcement> classesAnnouncements;

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public List<Announcement> getClassesAnnouncements() {
        return classesAnnouncements;
    }

    public void setClassesAnnouncements(List<Announcement> classesAnnouncements) {
        this.classesAnnouncements = classesAnnouncements;
    }

    public void addAnnouncement(Announcement announcement){
        this.classesAnnouncements.add(announcement);
    }

    public schoolClass( ){}
        public schoolClass(String name, int maxPart){
            this.className = name;
            this.maxParticipants = maxPart;
            this.students = new ArrayList<>();
            this.teachers = new ArrayList<>();

        }

        public int getId(){
            return this.id;
        }
        public void setId(int Id){
            this.id = Id;
        }
        public String getClassName(){
            return this.className;
        }
        public void setClassName(String className){
            this.className = className;
        }
        public int getMaxParticipants(){
            return this.maxParticipants;
        }
        public void setMaxParticipants(int maxParticipants){
            this.maxParticipants = maxParticipants;
        }

        public List<Student> getStudents(){
            return this.students;
        }
        public List<Teacher> getTeachers(){
            return this.teachers;
        }

        public void addStudent(Student toAdd){
            this.students.add(toAdd);
        }

        public void removeStudent(Student toRemove) {
            if(this.students.contains(toRemove)){
                this.students.remove(toRemove);
            }
        }

        public boolean containsStudent(Student toCheck){
            return this.students.contains(toCheck);
        }

        public boolean containsTeacher(Teacher toCheck){
            return this.teachers.contains(toCheck);
        }

        public void addTeacher(Teacher toAdd){
            this.teachers.add(toAdd);
        }

        public void removeTeacher(Teacher toRemove){
            this.teachers.remove(toRemove);
        }
}


