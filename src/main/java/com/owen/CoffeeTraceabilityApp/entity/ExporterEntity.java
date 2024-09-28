package com.owen.CoffeeTraceabilityApp.entity;

import java.util.Objects;
import jakarta.persistence.*;

@Entity
@Table(name = "exporters")
public class ExporterEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "exporter_id")
    private Long exporterId;

    
    private String name;

   
    @Column(unique = true)
    private String phoneNumber;
    
    
    @Column(unique = true)
    private String email;
    
    private String location;
    
    @Column(columnDefinition = "TEXT")
    private String shipping_details;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    // Constructors
    public ExporterEntity() {
    }

    public ExporterEntity(String name, String phoneNumber, String email, String location, String shipping_details, UserEntity user) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.location = location;
        this.shipping_details = shipping_details;
        this.user = user;
    }

    // Getters and Setters
    public Long getExporterId() {
        return exporterId;
    }

    public void setExporterId(Long exporterId) {
        this.exporterId = exporterId;
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

    public String getShipping_details() {
        return shipping_details;
    }

    public void setShipping_details(String shipping_details) {
        this.shipping_details = shipping_details;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    // toString Method
    @Override
    public String toString() {
        return "ExporterEntity{" +
                "exporterId=" + exporterId +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", location='" + location + '\'' +
                ", shipping_details='" + shipping_details + '\'' +
                ", user=" + user +
                '}';
    }

    // Equals and HashCode based on email and phoneNumber
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExporterEntity that = (ExporterEntity) o;
        return Objects.equals(email, that.email) &&
                Objects.equals(phoneNumber, that.phoneNumber);
    }

    @Override
    public int hashCode() {
        return (int) Objects.hash(email, phoneNumber);
    }
}
