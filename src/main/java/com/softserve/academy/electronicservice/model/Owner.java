package com.softserve.academy.electronicservice.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "owners")

public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "owner_id")
    private long id;

    private String firstName;

    private String lastName;

    private String fullName = firstName +" " + lastName;

    private String password;

    @Column(name = "create_date")
    private LocalDateTime createdDate;

    @MapsId
    @OneToMany(mappedBy = "owner")
    private List<Owner> deviceList;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public List<Owner> getDeviceList() {
        return deviceList;
    }

    public void setDeviceList(List<Owner> deviceList) {
        this.deviceList = deviceList;
    }

    public Owner(String firstName, String lastName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.createdDate = LocalDateTime.now();
    }

    public Owner() {

    }

    @Override
    public String toString() {
        return "Owner{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }
}
