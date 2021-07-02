package com.snoopbackendserver.SnoopBackendServer.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "users")
public class User implements Serializable {



    @Id
    @GeneratedValue
    private UUID id;
    @NotNull
    private String username;
    private String email;
    @NotNull
    private Long phoneNumber;
    private Boolean isActivated;
    private Boolean isVerified;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    List<Contacts> contactsList = new ArrayList<>();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Boolean getActivated() {
        return isActivated;
    }

    public void setActivated(Boolean activated) {
        isActivated = activated;
    }

    public Boolean getVerified() {
        return isVerified;
    }

    public void setVerified(Boolean verified) {
        isVerified = verified;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }

    public List<Contacts> getContactsList() {
        return contactsList;
    }

    public void setContactsList(List<Contacts> contactsList) {
        this.contactsList = contactsList;
    }



    public User(String username, String email, Long phoneNumber, Boolean isActivated, Boolean isVerified, LocalDateTime createdOn, LocalDateTime updatedOn) {
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.isActivated = isActivated;
        this.isVerified = isVerified;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", isActivated=" + isActivated +
                ", isVerified=" + isVerified +
                ", createdOn=" + createdOn +
                ", updatedOn=" + updatedOn +
                ", contactsList=" + contactsList +
                '}';
    }
}

