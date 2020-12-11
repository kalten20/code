package group4.school4you.Resources;

import group4.school4you.Entities.Admin;
import group4.school4you.Entities.User;
import group4.school4you.Repositories.AdminRepository;
import group4.school4you.Repositories.StudentRepository;
import group4.school4you.Repositories.UserJpaRepository;
import group4.school4you.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AdminResource {

    @Autowired
    private AdminRepository adminRepository;
    private UserService userService;
    private UserJpaRepository userRepository;
    private Admin admin;


    /**
     * This method gets an id and turns the isApproved of the belonging user to true.
     * @param id the id to get the belonging user from the database.
     */
    @PutMapping(path = "/admin/approve/{id}")
    public void approveUser(@PathVariable long id){
        if(userService.userExists(id)) {
            User toEdit = userService.findById(id);
            admin.setApproved(toEdit);
            userRepository.save(toEdit);
        } else {
            //eigentlich unnötig, weil admin ja sowieso nur alle angezeigt werden, die auch
            //in der Datenbank - nochmal überprüfen!!
            System.out.println("Fehlermeldung einfügen/User existiert nicht.");
        }
    }


}
