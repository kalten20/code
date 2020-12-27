package group4.school4you.Entities;

import java.util.Date;
import java.util.List;

public class Announcement {
    private long ID;
    private String sender;
    //sichtbarkeit hier so gemeint: "empfänger" / wer kann die Ankündigung sehen
    //array, weil mehrere rollen evtl sehen können -> zu enum noch ändern? generell rolle zu
    //enum ändern? -> mit ali reden!!
    private List<String> visibility;
    private String subject;
    private String content;
    private Date date;

    public Announcement(long id, String sender, List<String> visibility, String subject, String content
    , Date date){
        this.ID = id;
        this.sender = sender;
        this.visibility = visibility;
        this.subject = subject;
        this.content = content;
        this.date = date;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public List<String> getVisibility() {
        return visibility;
    }

    public void setVisibility(List<String> visibility) {
        this.visibility = visibility;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
