package group4.school4you.Entities;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class schoolClass {
        private int id;
        private String className;
        private int maxParticipants;

        public schoolClass(String name, int maxPart){
            this.className = name;
            this.maxParticipants = maxPart;
        }

        public int getId(){
            return this.id;
        }
        public void setId(int Id){
            this.id = Id;
        }
        public String getClassName(){
            return this.className;
        }
        public void setClassName(String className){
            this.className = className;
        }
        public int getMaxParticipants(){
            return this.maxParticipants;
        }
        public void setMaxParticipants(int maxParticipants){
            this.maxParticipants = maxParticipants;
        }
}


