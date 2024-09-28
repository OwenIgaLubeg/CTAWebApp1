package com.owen.CoffeeTraceabilityApp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cafeterias")
public class Cafeterias {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cafeteria_id")
    private Long cafeteriaId;

    private String name;

    @Column(unique = true)
    private String phoneNumber;

    @Column(unique = true)
    private String email;

    private String location;

    @Column(name = "served_coffee_details")
    private String servedCoffeeDetails;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    // Getters and Setters
    public Long getCafeteriaId() {
        return cafeteriaId;
    }

    public void setCafeteriaId(Long cafeteriaId) {
        this.cafeteriaId = cafeteriaId;
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

    public String getServedCoffeeDetails() {
        return servedCoffeeDetails;
    }

    public void setServedCoffeeDetails(String servedCoffeeDetails) {
        this.servedCoffeeDetails = servedCoffeeDetails;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
