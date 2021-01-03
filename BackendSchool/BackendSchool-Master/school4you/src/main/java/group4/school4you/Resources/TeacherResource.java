package group4.school4you.Resources;


import group4.school4you.Entities.*;
import group4.school4you.Repositories.*;
import group4.school4you.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TeacherResource {
    @Autowired
    private TeacherRepository teacherRepository;
    private UserJpaRepository userRepository;
    private AnnouncementRepository announcementRepository;
    private schoolClassRepository schoolClassRepository;
    private SickNoteRepository sickNoteRepository;
    private ExamRepository examRepository;
    private UserService userService;
    private Teacher teacherToWorkWith;



    /**
     * This methods gets an id from the frontend of a certain teacher and searches in the database for all classes
     * which contain this teacher in its list of teachers. Then it collects all this classes to give a list of all
     * classes teached by this teacher back to the frontend.
     * @param id To get the teacher from the repository.
     * @return A list of all classes which the teachers teaches.
     */
    @GetMapping(path = "/teacher/classes/{id}")
    public List<schoolClass> getClasses(@PathVariable long id){
        List<schoolClass> allClasses = schoolClassRepository.findAll();
        List<schoolClass> toReturn = null;
        Teacher toGetClasses = (Teacher) teacherRepository.findById(id).get();

        for(int i = 0; i < allClasses.size(); i++){
            if(allClasses.get(i).containsTeacher(toGetClasses)) {
                toReturn.add(allClasses.get(i));
            }
        }
        return toReturn;
    }


    /**
     * A teacher can create a new exam for a certain class on a certain date. For this the method gets all the infos
     * from the frontend and creates a exam with the teacherId, a classId(which class will write the exam), a subject -
     * in which subject the exam will be, a date when the exam will be and a short description of the exam.
     * @param id The teacher id to create an exam with this id.
     * @param exam All the requested data from the frontend to create a new exam in the backend.ja
     */
    @PostMapping(path = "/teacher/exam/neu/{teacherId}")
    public void createExam(@PathVariable long id, @RequestBody Exam exam){
         Teacher teacher = (Teacher) userService.findById(id);
         examRepository.save(teacher.createExam(id, exam.getClassID(), exam.getSubject(),
                 exam.getDate(), exam.getDescription()));
    }

    /**
     * A teacher can edit an exam. He can change the class, the subject, the date or the description.
     * @param examId The exam which is changed in the database.
     * @param exam The data for changing the certain exam.
     */
    @PutMapping(path = "/teacher/exam/{examId}")
    public void editExam(@PathVariable long examId, @RequestBody Exam exam){
        Exam toEdit = examRepository.findById(examId).get();
        teacherToWorkWith.editExam(toEdit, exam.getClassID(), exam.getSubject(), exam.getDate(), exam.getDescription());
        examRepository.save(toEdit);
    }

    /**
     * Teachers can create a sickNote they can send to the secretary. The secretary can approve the sickNote -> after
     * that we got notes in the classes the teacher teaches.
     * @param id The id of the teacher who reports himself sick.
     * @param sickNote The data from the frontend for the sickNote.
     */
    @PostMapping(path = "/teacher/sickNote/{teacherId}")
    public void createSickNote(@PathVariable long id, @RequestBody SickNote sickNote){
        Teacher teacher = (Teacher) userService.findById(id);
        sickNoteRepository.save(teacher.createSickNote(id, sickNote.getDate(), teacher.getEmail(), sickNote.getContent(),
                teacher.getRole()));
    }


    @GetMapping(path = "/teacher/announcements/getAll/{teacherId}")
    public List<Announcement> getAnnouncements(@PathVariable long id){
        List<Announcement> allAnnouncements = announcementRepository.findAll();
        List<Announcement> allAnnouncementsForThisTeacher = null;
        List<schoolClass> allClasses = schoolClassRepository.findAll();
        List<schoolClass> classesOfTeacher = null;

        Teacher teacher = (Teacher) userService.findById(id);

        //Es werden alle Klassen des Lehrers herausgesucht
        for(int i = 0; i < allClasses.size(); i++){
            if(allClasses.get(i).containsTeacher(teacher)){
                classesOfTeacher.add(allClasses.get(i));
            }
        }
        //Es werden alle ankündigungen durchsucht und die, die für lehrer sichtbar sind, in die liste für den Lehrer
        //hinzugefügt
        for(int i = 0; i < allAnnouncements.size(); i++){
            if(allAnnouncements.get(i).getVisibility().contains("teacher")){
                allAnnouncementsForThisTeacher.add(allAnnouncements.get(i));
            }
        }

        //es werden alle klassen des lehrers durchlaufen und die klassen ankündiungen herausgezogen -> diese werden dann
        //in die liste für den lehrer hinzugefügt
        for(int i = 0; i < classesOfTeacher.size(); i++){
            List<Announcement> classAnnouncements = classesOfTeacher.get(i).getClassesAnnouncements();
            for(int j = 0; j < classAnnouncements.size(); j++){
                allAnnouncementsForThisTeacher.add(classAnnouncements.get(j));
            }
        }

        return allAnnouncementsForThisTeacher;


    }



    /**
     * With this method the teacher can create a new announcement for a certain class. This is the main difference to the
     * secretary announcement. The secretary can create an announcement for a certain role. The teacher can create an
     * announcement for a certain class.
     * @param announcement The announcement which is saved in the database.
     */
    @PostMapping(path = "/teacher/announcements/createAnnouncement/{teacherId}")
    public void createAnnouncement(@PathVariable long id, @RequestBody Announcement announcement){
        Teacher teacher = (Teacher) userService.findById(id);

        //Lehrer erstellt neue Ankündigung
        Announcement newAnnouncement = teacher.createAnnouncement(teacher.getFirstName()+" "+teacher.getLastName(),
                announcement.getVisibility(), announcement.getSubject(), announcement.getContent(), announcement.getDate(),
                announcement.getClassID());
        //Announcement wird im repository gespeichert mit entsprechender classID
        announcementRepository.save(newAnnouncement);

        //Entsprechende Klasse bekommt announcement noch in ihre liste hinzugefügt -> zum anzeigen um einiges leichter
        schoolClass toEdit = schoolClassRepository.findById(announcement.getClassID()).get();
        toEdit.addAnnouncement(newAnnouncement);
        schoolClassRepository.save(toEdit);
    }
}
