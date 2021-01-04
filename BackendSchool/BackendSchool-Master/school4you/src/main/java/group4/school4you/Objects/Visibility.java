package group4.school4you.Objects;

import org.springframework.context.annotation.Bean;

import javax.persistence.Entity;
import java.util.List;

public class Visibility {

    private List<String> roles;

    public Visibility() {}
    public Visibility (List<String> roles) {
        this.roles = roles;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }



}
