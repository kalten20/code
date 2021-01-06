package group4.school4you.Entities;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.util.Date;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class SickNote {
    private long id;
    private long userId;
    private Date date;
    private String email;
    private String content;
    private String role;
    private boolean isApproved = false;

    public SickNote(long userId, Date date, String email,String content, String role){
        this.userId = userId;
        this.date = date;
        this.email = email;
        this.content = content;
        this.role = role;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean getApproved(){
        return isApproved;
    }

    public void setApproved(){isApproved = true;}
}
