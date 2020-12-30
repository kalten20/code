package group4.school4you.Resources;

import group4.school4you.Entities.*;
import group4.school4you.Repositories.*;
import group4.school4you.Services.SecretaryService;
import group4.school4you.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class SecretaryResource {

    @Autowired
    private UserJpaRepository userRepository;
    private ParentRepository parentRepository;
    private StudentRepository studentRepository;
    private SchoolClassRepository schoolClassRepository;
    private AnnouncementRepository announcementRepository;

    private Secretary secretary;

    private UserService userService;
    private SecretaryService secretaryService;






    /**
     * This method allows a secretary member to approve an user as a parent or a child in the database.
     * @param id The id to get the belonging user from the database.
     */
    @PutMapping(path = "/secretary/approve/{id}")
    public void approveUser(@PathVariable long id){
        User toEdit = userService.findById(id);
        secretary.setApproved(toEdit);
        userRepository.save(toEdit);
    }

    /**
     * This method allows a secretary member to change the mail adress from the parent or student.
     * @param id The id to identify the belonging user from the database.
     * @param mailAdress The new mail adress the user gets.
     */
    @PutMapping(path = "/secretary/editUser/{id}/{mailAdress}")
    public void editUsersMail(@PathVariable long id, @PathVariable String mailAdress){
        User toEdit = userService.findById(id);
        secretary.changeUsersMailAdress(toEdit, mailAdress);
        userRepository.save(toEdit);
    }

    /**
     * This method allows a secretary member to change the name of a student or parent.
     * @param id The id to identify the belonging user from the database
     * @param firstName The new first name of the user.
     * @param lastName The new last name of the user.
     */
    @PutMapping(path = "/secretary/editUser/{id}/{firstName}/{lastName}")
    public void editUsersName(@PathVariable long id, @PathVariable String firstName, @PathVariable String lastName){
        User toEdit = userService.findById(id);
        secretary.changeUsersName(toEdit, firstName, lastName);
        userRepository.save(toEdit);
    }

    /**
     * This method allows a secretary member to change the class of a student.
     * @param id The id to identify the belonging user from the database
     * @param classID The new class of the student.
     */
    @PutMapping(path = "/secretary/editUser/{id}/{classID}")
    public void editUsersClass(@PathVariable long id, @PathVariable long classID){
        Student toEdit = (Student) userService.findById(id);
        toEdit.setClassId(classID);
        userRepository.save(toEdit);
    }

    /**
     * This method allows a secretary member to delete an user.
     * @param id The id to get the user which is deleted.
     */
    @DeleteMapping(path = "/secretary/deleteUser/{id}" )
    @CrossOrigin(origins = "http://localhost:4200")
    public void deleteUser(@PathVariable long id) {
        userRepository.deleteById(id);
    }

    //OFFEN HALTEN : muss das Sekretariat die familienID ändern können?

    //==========================KLASSEN=================================


    //wollen wir wirklich eine maximale teilnehmeranzahl? kommt in der praxis ja eigentlich nicht vor,
    //dass eine klasse so groß wird?
    //wie handhaben wir es wenn eine klasse mit diesem namen schon existiert?
    /**
     * With this method a secretary user creates a new class. It checks if the class is already existing
     * in the database. If not it creates a new one.
     * @param className The name of the new class.
     * @param maxParticipants Maximum of participants the new class can take.
     * @return true if the class was added successfully. False if not(class already exists in database)
     */
//    @PostMapping(path = "/classes/{className}/{maxParticipants}/neu")
//    public boolean createClass(@PathVariable String className, @PathVariable int maxParticipants) {
//        if(secretaryService.findByName(className).equals(null)){
//            schoolClassRepository.save(secretary.createNewClass(className, maxParticipants));
//            return true;
//        } else {
//            return false;
//        }
//    }

    /**
     * This method allows the secretary to delete a class from the database.
     * @param className The name to get the class from the database.
     * @return false if the class doesn't exist in the database. True if existed and deleted
     * successfully.
     */
    @DeleteMapping(path = "/classes/delete/{className}")
    public boolean deleteClass(@PathVariable String className){
        if(secretaryService.findByName(className).equals(null)){
            return false;
        }else {
            schoolClassRepository.delete(secretaryService.findByName(className));
            return true;
        }
    }

    /**
     * The secretary can change a classes name.
     * @param className The current name of a class.
     * @param newClassName The new name for the class.
     */
    @PutMapping(path = "/classes/changeName/{className}/{newClassName}")
    public void changeClassName(@PathVariable String className, @PathVariable String newClassName){
        SchoolClass toChange = secretaryService.findByName(className);
        secretary.changeClassName(toChange, newClassName);
        schoolClassRepository.save(toChange);
    }

    /**
     * The secretary can change the amount of maximum participants in a class.
     * @param className The class which the maximum of participants will be changed.
     * @param newMaxParticipants The new maximum of participants.
     */
//    @PutMapping(path = "/classes/changeMaxPart/{className}/{newMaxPart}")
//    public void changeMaxPart(@PathVariable String className, @PathVariable int newMaxParticipants){
//        SchoolClass toChange = secretaryService.findByName(className);
//        secretary.changeMaxParticipants(toChange, newMaxParticipants);
//        schoolClassRepository.save(toChange);
//    }

    /**
     * The secretary can add an user to a class.
     * @param className The class the secretary is editing.
     * @param studentId The user which is added to the class.
     */
    @PutMapping(path = "/classes/addStudent/{className}/{studentId}")
    public void addStudentToClass(@PathVariable String className, @PathVariable long studentId){
        SchoolClass toEdit = secretaryService.findByName(className);
        User toAdd = userService.findById(studentId);
        secretary.addStudentToClass(toEdit, (Student) toAdd);
    }

    /**
     * The secretary can remove a user from a class.
     * @param className The class to remove the user from.
     * @param studentId The user which is removed from the class.
     */
    @DeleteMapping(path = "/classes/removeStudent/{className}/{studentId}")
    public void removeStudentFromClass(@PathVariable String className, @PathVariable long studentId){
        SchoolClass toEdit = secretaryService.findByName(className);
        User toRemove = userService.findById(studentId);
        secretary.removeStudentFromClass(toEdit, (Student) toRemove);
        schoolClassRepository.save(toEdit);
    }

    /**
     * Secretary can add a teacher to a class. The class contains a list of all teachers who teaches
     * the class.
     * @param className The class the teacher will be add to.
     * @param teacherId The teacher id to get the teacher which will be added.
     */
    @PutMapping(path = "/classes/addTeacher/{className}/{teacherId}")
    public void addTeacherToClass(@PathVariable String className, @PathVariable long teacherId){
        SchoolClass toEdit = secretaryService.findByName(className);
        User toAdd = userService.findById(teacherId);
        secretary.addTeacherToClass(toEdit, (Teacher) toAdd);
    }

    /**
     * Secretary can remove a teacher from a class.
     * @param className The class which the teacher is removed from.
     * @param teacherId The teacher who will be removed from the class. 
     */
    @DeleteMapping(path = "/classes/removeTeacher/{className}/{teacherId}")
    public void removeTeacherFromClass(@PathVariable String className, @PathVariable long teacherId){
        SchoolClass toEdit = secretaryService.findByName(className);
        User toRemove = userService.findById(teacherId);
        secretary.removeTeacherFromClass(toEdit, (Teacher) toRemove);
    }


    //===========================Ankündigungen==============================================

    /**
     * To get all announcements from the database.
     * @return A list of all announcements in the database.
     */
    @GetMapping(path = "/announcements/getAll")
    public List<Announcement> getAllAnnouncements(){
        return announcementRepository.findAll();
    }

    /**
     * This method gets all announcements depend on its role. So for example we can get all announcements for students,
     * all for teachers or all for parents.
     * @param role The role we want the announcements from.
     * @return A list of all announcements of the certain role.
     */
    @GetMapping(path = "/announcements/getByRole/{role}")
    public List<Announcement> getAnnouncement(@PathVariable String role){
        List<Announcement> allAnnouncements = announcementRepository.findAll();
        List<Announcement> toReturn = null;

        if(role.equals("student")){
            for(int i = 0; i < allAnnouncements.size(); i++){
                if(allAnnouncements.get(i).getVisibility().contains("student")){
                    toReturn.add(allAnnouncements.get(i));
                }
            }
        } if(role.equals("teacher")){
            for(int i = 0; i < allAnnouncements.size(); i++){
                if(allAnnouncements.get(i).getVisibility().contains("teacher")){
                    toReturn.add(allAnnouncements.get(i));
                }
            }
        } if(role.equals("parent")){
            for(int i = 0; i < allAnnouncements.size(); i++){
                if(allAnnouncements.get(i).getVisibility().contains("parent")){
                    toReturn.add(allAnnouncements.get(i));
                }
            }
        }
        return toReturn;
    }

    /**
     * With this method the secretary can create a new announcement.
     * @param announcement The announement which is saved in the database.
     */
    @PostMapping(path = "/announcements/createAnnouncement")
    public void createAnnouncement(@RequestBody Announcement announcement){
        announcementRepository.save(secretary.createAnnouncement(announcement.getID(),"Sekretariat",
                announcement.getVisibility(), announcement.getSubject(), announcement.getContent(),
                announcement.getDate()));
    }

    /**
     * This method helps the secretary to edit an announcement.
     * @param id    To find the announcement in the database.
     * @param announcement
     */
    @PutMapping(path = "/announcements/editAnnouncement/{id}")
    public void editAnnouncement(@PathVariable long id, @RequestBody Announcement announcement){
        Announcement toEdit = announcementRepository.findById(id).get();
        secretary.editAnnouncement(toEdit, id, announcement.getSender(), announcement.getVisibility(),
                announcement.getSubject(), announcement.getContent(), announcement.getDate());
        announcementRepository.save(toEdit);
    }

    /**
     * This method deletes an announcement from the database.
     * @param id To find the announcement.
     */
    @DeleteMapping(path = "/announcements/deleteAnnouncement/{id}")
    public void deleteAnnouncement(@PathVariable long id){
        announcementRepository.deleteById(id);
    }
}

