package model;


import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="doctors")
@NamedQueries({
        @NamedQuery(name = "getId", query = "select e from Doctors e"),
        @NamedQuery(name = "getFirstName", query = "select e from Doctors e"),
        @NamedQuery(name = "getLastName", query = "select e from Doctors e"),
        @NamedQuery(name = "getSpecialization", query = "select e from Doctors e"),
        @NamedQuery(name = "getRoom", query = "select e from Doctors e"),
})
public class Doctor {

    @Id
    @Column (name = "id")
    private int id;
    @Column (name = "first_name")
    private String firstName;
    @Column (name = "last_name")
    private String lastName;
    @Column (name = "specializations")
    private String specializations;
    @Column (name = "room")
    private String room;

    public Doctor(int id, String firstName, String lastName, String specializations, String room) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.specializations = specializations;
        this.room = room;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSpecializations(String specializations) {
        this.specializations = specializations;
    }

    public int getId() {
        return id;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSpecializations() {
        return specializations;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
}
