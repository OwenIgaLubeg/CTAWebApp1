package com.owen.CoffeeTraceabilityApp.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "intermediaries")
public class IntermediaryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "intermediary_id")
    private Long intermediaryId;

    private String name;

    private String region;

    @Column(unique = true)
    private String phoneNumber;

    @Column(unique = true)
    private String email;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UserEntity user;

    // Getters and Setters
    public Long getIntermediaryId() {
        return intermediaryId;
    }

    public void setIntermediaryId(Long intermediaryId) {
        this.intermediaryId = intermediaryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
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

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
