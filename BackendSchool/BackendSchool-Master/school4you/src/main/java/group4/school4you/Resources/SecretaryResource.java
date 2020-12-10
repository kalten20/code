package group4.school4you.Resources;

import group4.school4you.Repositories.SecretaryRepository;
import group4.school4you.Repositories.StudentRepository;
import group4.school4you.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class SecretaryResource {

    @Autowired
    private SecretaryRepository secretaryRepository;

    private UserService userService;




}
