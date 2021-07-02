package com.snoopbackendserver.SnoopBackendServer.Model;

import com.snoopbackendserver.SnoopBackendServer.Model.User;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "user_contact")
public class Contacts {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID contact_id;
    @Column(name = "user_id")
    private  UUID user_id;

    public UUID getContact_id() {
        return contact_id;
    }

    public void setContact_id(UUID contact_id) {
        this.contact_id = contact_id;
    }

    public UUID getUser_id() {
        return user_id;
    }

    public void setUser_id(UUID user_id) {
        this.user_id = user_id;
    }
}
