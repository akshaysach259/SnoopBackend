package com.snoopbackendserver.SnoopBackendServer.Controller;

import com.snoopbackendserver.SnoopBackendServer.Model.User;
import com.snoopbackendserver.SnoopBackendServer.Services.UserService;
import com.snoopbackendserver.SnoopBackendServer.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/getAllUsers")
    public List<User> getAllUsers(){
        return  userService.getAllUsers();
    }

    @PostMapping(value = "/saveUser", consumes = "application/json" ,produces = "application/json")
    public ResponseEntity<Void> saveUser(@RequestBody User user){
        ResponseUtils serviceResponse = userService.saveUser(user);
        if ( serviceResponse == ResponseUtils.SAVED) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }else if(serviceResponse == ResponseUtils.ALREADYEXISTS) {
            return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
        }else {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("/updateUser")
    public ResponseEntity<Void> updateUser(@RequestBody User user){
        ResponseUtils serviceResponse = userService.saveUser(user);
        if ( serviceResponse == ResponseUtils.SAVED) {
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/userById/{userId}")
    public User userById(@PathVariable("userId") String id){
        return userService.getUserById(UUID.fromString(id));
    }

    @GetMapping("/getUserByPhoneNumber/{phoneNumber}")
    public User userByPhoneNumber(@PathVariable("phoneNumber") Long phoneNumber){
        return userService.getUserByPhoneNumber(phoneNumber);
    }

    @PostMapping("/addContact/{phoneNumber}")
    public ResponseEntity<Void> addContacts(@PathVariable("phoneNumber") Long phoneNumber, @RequestBody User user){
        ResponseUtils responseUtils = userService.addContacts(phoneNumber,user);
        if(responseUtils == ResponseUtils.UPDATED) {
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/deleteUser/{phoneNumber}")
    public ResponseEntity<Void> deleteUserByPhoneNumber(@PathVariable Long phoneNumber){
        ResponseUtils response = userService.deleteUser(phoneNumber);
        if(response == ResponseUtils.DELETED){
            return new ResponseEntity<>(HttpStatus.OK);
        }else if(response == ResponseUtils.DOESNOTEXISTS){
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        } else{
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
    @GetMapping("deleteAllUsers")
    public ResponseEntity<Void> deleteAllUsers(){
        if (userService.deleteAllUsers() == ResponseUtils.DELETED) {
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
