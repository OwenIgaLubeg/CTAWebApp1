package com.owen.CoffeeTraceabilityApp.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "processors")
public class ProcessorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "processor_id")
    private Long processorId;

    
    private String name;

    @Column(nullable = false)
    private String type;

   
    @Column(unique = true)
    private String phoneNumber;

   
    @Column(unique = true)
    private String email;

   
    private String location;

    @Lob
    private String processingDetails;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;
    // Getters and Setters

    public Long getProcessorId() {
        return processorId;
    }

    public void setProcessorId(Long processorId) {
        this.processorId = processorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getProcessingDetails() {
        return processingDetails;
    }

    public void setProcessingDetails(String processingDetails) {
        this.processingDetails = processingDetails;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProcessorEntity that = (ProcessorEntity) o;
        return Objects.equals(processorId, that.processorId) && Objects.equals(name, that.name) && Objects.equals(type, that.type) && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(email, that.email) && Objects.equals(location, that.location) && Objects.equals(processingDetails, that.processingDetails) && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(processorId, name, type, phoneNumber, email, location, processingDetails, user);
    }
}