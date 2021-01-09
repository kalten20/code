package group4.school4you.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Presence {

    @Id
    @GeneratedValue
    private Long id;
    private Long userId;
    private Long appointmentId;
    private boolean isPresent;
}
