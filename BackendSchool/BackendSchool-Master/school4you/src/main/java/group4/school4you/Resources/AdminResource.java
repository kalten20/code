package group4.school4you.Resources;

import group4.school4you.Repositories.AdminRepository;
import group4.school4you.Repositories.StudentRepository;
import group4.school4you.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AdminResource {

    @Autowired
    private AdminRepository adminRepository;

    private UserService userService;





}
