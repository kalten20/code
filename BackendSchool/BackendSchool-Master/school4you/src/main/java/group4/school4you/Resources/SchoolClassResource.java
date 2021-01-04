package group4.school4you.Resources;

import group4.school4you.Entities.SchoolClass;
import group4.school4you.Entities.User;
import group4.school4you.Services.SchoolClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.MediaSize;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class SchoolClassResource {

    @Autowired
    private SchoolClassService schoolClassService;

    @PostMapping(path = "/schoolClass/new/{name}")
    public SchoolClass createClass(@PathVariable String name ) {
        //HIER THROW PARSING EXCEPTION
        return schoolClassService.create(name);
    }

    @PutMapping(path = "/schoolClass/{targetClassName}/add/{role}/{email}")
    public User addUserToClass(@PathVariable String targetClassName,
            @PathVariable String role,
                               @PathVariable String email) {
        //HIER THROW PARSING EXCEPTIONs
        SchoolClass targetClass =
                schoolClassService.findByName(targetClassName);

        return schoolClassService.addUserByRoleAndEmail(role, email, targetClass);

    }

    @PutMapping (path = "/schoolClass/{targetClassName}/remove/{role}/{id}")
    public User removeUserFromClass(@PathVariable String targetClassName,
                               @PathVariable String role,
                               @PathVariable Long id) {
        SchoolClass targetClass =
                schoolClassService.findByName(targetClassName);
        return schoolClassService.removeUser(role, id, targetClass);

    }


    @GetMapping (path = "/schoolClass/all")
    public List<String> getAllClassNames() {
        return schoolClassService.getAllClassNames();
    }
    @GetMapping (path = "/schoolClass/{targetClass}")
    public SchoolClass getClass(@PathVariable String targetClass) {
        return schoolClassService.findByName(targetClass);
    }

    @GetMapping (path = "/students/unavailable")
    public List<String> getUnavailableStudentEmails () {
        return schoolClassService.getUnavailableStudentEmails();
    }

}
