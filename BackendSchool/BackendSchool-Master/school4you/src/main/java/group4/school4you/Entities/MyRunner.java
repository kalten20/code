package group4.school4you.Entities;

import group4.school4you.Objects.Role;
import group4.school4you.Repositories.InboxRepository;
import group4.school4you.Repositories.StudentRepository;
import group4.school4you.Repositories.TeacherRepository;
import group4.school4you.Repositories.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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
    private InboxRepository inboxRepository;

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


//        userRepository.findAll().forEach((city) -> {
//            logger.info("{}", city);
//        });
    }
}
