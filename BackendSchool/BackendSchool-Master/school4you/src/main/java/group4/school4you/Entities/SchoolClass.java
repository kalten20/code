package group4.school4you.Entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class SchoolClass {
    @Id
    @GeneratedValue
    private int id;
        private String className;
        @OneToMany
        private List<Student> students;
        @ManyToMany
        private  List<Teacher> teachers;



    public SchoolClass( ){}
        public SchoolClass(String name){
            this.className = name;
            students = new ArrayList<>();
            teachers = new ArrayList<>();
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
        if (teachers.contains(toRemove)) {
            this.teachers.remove(toRemove);
        }
        }
}


