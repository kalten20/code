package group4.school4you.Entities;

import group4.school4you.Objects.Role;
import group4.school4you.Objects.Subject;
import group4.school4you.Repositories.*;
import group4.school4you.Services.AppointmentService;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class MyRunner implements CommandLineRunner {

//    private static final Logger logger = (Logger) LoggerFactory.getLogger(MyRunner.class);

    @Autowired
    private UserJpaRepository userRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private SecretaryRepository secretaryRepository;
    @Autowired
    private ParentRepository parentRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private InboxRepository inboxRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    AppointmentService appointmentService;
    @Autowired
    SchoolClassRepository schoolClassRepository;

    @Override
    public void run(String... userRepository) throws Exception {

        if (!inboxRepository.existsByRole(Role.SECRETARY)) {
            inboxRepository.save(new Inbox(Role.SECRETARY));
        }
        if (!inboxRepository.existsByRole(Role.TEACHER)) {
            inboxRepository.save(new Inbox(Role.TEACHER));
        }

        if (!inboxRepository.existsByRole(Role.STUDENT)) {
            inboxRepository.save(new Inbox(Role.STUDENT));
        }
        if (!inboxRepository.existsByRole(Role.PARENT)) {
            inboxRepository.save(new Inbox(Role.PARENT));
        }



//        System.out.println(appointmentService.findAvailableTeachers(
//                appointmentRepository.findById(new Long("92")).get()
//        ));

//        SchoolClass testClass = schoolClassRepository.findById(new Long("17")).get();
//        List<Long> teacherIds = appointmentService.getTeacherIds(testClass);
//        System.out.println(teacherIds);




//        appointmentRepository.save(new Appointment(new Long(17),
//                new Long(7),LocalDate.now(),"08:00 _ 10:00", Subject.CHEMIE));


//        Appointment testTestAppoint = new Appointment(new Long(17),
//                new Long(7), LocalDate.now(),"14:00 _ 16:00" );
        //appointmentRepository.save(testTestAppoint);
        System.out.println( "status is : " +
                appointmentService.getFieldStatus(
                        new Appointment(new Long(21),
                                new Long(7), LocalDate.now(),"12:00 _ 14:00" )
                ) );


       List<Appointment> testUnavailable =
                appointmentRepository.findAllByTeacherIdAndDateAndSlotAndClassIdNot(new Long(7),
                        LocalDate.now(),"12:00 _ 14:00",new Long("36"));

        System.out.println(testUnavailable.size());

        System.out.println(appointmentService.checkAvailability(new Appointment(new Long(17),
                new Long(7),LocalDate.now(),"08:00 _ 10:00")));




//        userRepository.findAll().forEach((city) -> {
//            logger.info("{}", city);
//        });
    }
}
