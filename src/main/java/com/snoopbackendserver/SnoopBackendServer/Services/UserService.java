package com.snoopbackendserver.SnoopBackendServer.Services;

import com.snoopbackendserver.SnoopBackendServer.Model.Contacts;
import com.snoopbackendserver.SnoopBackendServer.Model.User;
import com.snoopbackendserver.SnoopBackendServer.Repositiories.UserJPARepository;
import com.snoopbackendserver.SnoopBackendServer.utils.ResponseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Service
public class UserService {
    Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserJPARepository userJPARepository;

    public List<User> getAllUsers() {
        return  userJPARepository.findAll();
    }

    public ResponseUtils saveUser(User user) {
        if (userJPARepository.getUserByPhoneNumber(user.getPhoneNumber()).orElse(null) == null){
            user.setCreatedOn(LocalDateTime.now());
            user.setUpdatedOn(LocalDateTime.now());
            user.setActivated(true);
            user.setVerified(true);
            userJPARepository.save(user);
            log.info("saved User "+ user.toString());
            return ResponseUtils.SAVED;
        } else {
            log.info("User Already Exists");
            return ResponseUtils.ALREADYEXISTS;
        }
//        Contacts contacts = new Contacts();
//        User user1 = new User("Abhishek","abhishekSharma@gmail.com",7289043749L,true,true, LocalDateTime.now(),LocalDateTime.now());
//        user1.getContactsList().add(contacts);

    }

    public User getUserById(UUID uuid) {
        return userJPARepository.getById(uuid);
    }

    public User getUserByPhoneNumber(Long phoneNumber) {
        return userJPARepository.getUserByPhoneNumber(phoneNumber).orElse(null);
    }

    public ResponseUtils addContacts(Long phoneNumber, User currentUser) {
        User newContactByPhoneNumber = userJPARepository.getUserByPhoneNumber(phoneNumber).orElse(null);
        if(newContactByPhoneNumber != null && currentUser != null) {
            Contacts newContact = new Contacts();
            newContact.setUser_id(newContactByPhoneNumber.getId());
            currentUser.getContactsList().add(newContact);
            userJPARepository.save(currentUser);
            return ResponseUtils.UPDATED;
        }else {
            return  ResponseUtils.DOESNOTEXISTS;
        }
    }

    public ResponseUtils deleteUser(Long phoneNumber){
        User currentUser = userJPARepository.getUserByPhoneNumber(phoneNumber).orElse(null);
        if (currentUser != null) {
            userJPARepository.delete(currentUser);
            return ResponseUtils.DELETED;
        }else {
            return ResponseUtils.DOESNOTEXISTS;
        }
    }

    public ResponseUtils deleteAllUsers(){
        userJPARepository.deleteAll();
        return ResponseUtils.DELETED;
    }
}
