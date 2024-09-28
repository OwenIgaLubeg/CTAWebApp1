package com.owen.CoffeeTraceabilityApp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "roastingFirms")
public class RoastingFirms {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roaster_id")
    private Long roasterId;

    private String name;

    @Column(unique = true)
    private String phoneNumber;

    @Column(unique = true)
    private String email;

    private String location;

    private String roastProfiles;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    // Getters and Setters
    public Long getRoasterId() {
        return roasterId;
    }

    public void setRoasterId(Long roasterId) {
        this.roasterId = roasterId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRoastProfiles() {
        return roastProfiles;
    }

    public void setRoastProfiles(String roastProfiles) {
        this.roastProfiles = roastProfiles;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
