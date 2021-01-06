package group4.school4you.Resources;

import group4.school4you.Entities.*;
import group4.school4you.Repositories.*;
import group4.school4you.Services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ParentResource {

    private UserService userService;
    private SickNoteRepository sickNoteRepository;
    private StudentRepository studentRepository;
    private ParentRepository parentRepository;
    private AnnouncementRepository announcementRepository;
    private schoolClassRepository schoolClassRepository;
    private Parent parent;


    /**
     * Parents are able to get all announcements for them. This means they get all general announcements which are
     * for parents and on top of that they get all announcements of their children.
     * @param parentID the id of the parent to find the parent and then the children and their classes in the database.
     * @return a list of all announcement which are relevant for the parents.
     */
    @GetMapping(path = "/parent/Announcements/{parentID}")
    public List<Announcement> getAllAnnouncements(@PathVariable long parentID){
        Parent parent = (Parent) parentRepository.getOne(parentID);
        List<Announcement> allAnnouncements = announcementRepository.findAll();
        List<schoolClass> allSchoolClasses = schoolClassRepository.findAll();
        List<Announcement> announcementsForParent = null;
        List<Student> kids = getChildren(parentID);

        //ankündigungen von eltern generell werden geholt
        for(int i = 0; i < allAnnouncements.size(); i++){
            if(allAnnouncements.get(i).getVisibility().contains("parent")){
                announcementsForParent.add(allAnnouncements.get(i));
            }
        }
        //Klassen der kinder werden gesucht um anschließend die ankündigungen der klassen zu suchen
        List<schoolClass> classesOfChildren = null;
        List<Long> classIDsChildren = null;

        for(int i = 0; i < kids.size(); i++){
            classIDsChildren.add(kids.get(i).getClassId());
        }

        for(int i = 0; i < classIDsChildren.size(); i++){
             for(int j = 0; j < allSchoolClasses.size(); j++){
                 if(classIDsChildren.get(i) == allSchoolClasses.get(j).getId()){
                     classesOfChildren.add(allSchoolClasses.get(j));
                 }
             }

        }

        //ankündigungen der einzelnen klassen der kinder werden zur gesamt ankündigungsliste hinzugefügt
        for(int i = 0; i < classesOfChildren.size(); i++){
            List<Announcement> classAnnouncements = classesOfChildren.get(i).getClassesAnnouncements();
            announcementsForParent.addAll(classAnnouncements);
        }
        return announcementsForParent;
    }


    /* HIER IDEE FÜRS FRONTEND:
    auf einer seite einfach alle kinder anzeigen und dann über button oder wie auch immer die optionen "krankmeldung
    erstellen" und "noten anzeigen" und je nachdem dann passende funktion aufrufen (hier jetzt mit email des studenten -
    genauso gut auch die id möglich, dann müss mas nur kurz hier ändern im code)
     */
    /**
     * To get all kids of a parent.
     * @param parentID the family id is used to find all kids of the family in the database.
     * @return a list of all students in the family.
     */
    @GetMapping(path = "/parent/getChildren/{parentID}")
    public List<Student> getChildren(@PathVariable long parentID){
        List<Student> children = null;
        Parent parent = (Parent) parentRepository.getOne(parentID);
        long familyID = parent.getFamilyId();
        List<User> allStudents = studentRepository.findAll();
        for(int i = 0; i < allStudents.size(); i++){
            Student potentiallyKid = (Student) allStudents.get(i);
            if(potentiallyKid.getFamilyId() == familyID){
                children.add(potentiallyKid);
            }
        }
        return children;
    }

    /**
     * This method allows a parent to create a sick note for a student.
     * @param studentMail Required student mail to get the right student from the database.
     * @param sickNote a sick note is created and saved to the database.
     */
    @PostMapping(path = "/parent/sickNote/{EmailStudent}")
    public void createSickNote(@PathVariable String studentMail, @RequestBody SickNote sickNote){
        Student student = (Student) studentRepository.findByEmail(studentMail);
        sickNoteRepository.save(parent.createSickNote(student.getId(), sickNote.getDate(), studentMail,
                sickNote.getContent(), student.getRole()));
    }

    @GetMapping(path = "/parent/studentGrades/{EmailStudent}")
    public List<Grade> getStudentGrades(@PathVariable String studentMail){
        Student student = (Student) studentRepository.findByEmail(studentMail);
        return student.getStudentsGrades();
    }
}
