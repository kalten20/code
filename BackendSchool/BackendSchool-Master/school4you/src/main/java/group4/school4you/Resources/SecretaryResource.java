package group4.school4you.Resources;

import group4.school4you.Entities.Secretary;
import group4.school4you.Entities.User;
import group4.school4you.Repositories.SecretaryRepository;
import group4.school4you.Repositories.UserJpaRepository;
import group4.school4you.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class SecretaryResource {

    @Autowired
    private SecretaryRepository secretaryRepository;
    private UserJpaRepository userRepository;
    private UserService userService;
    private Secretary secretary;


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


}
