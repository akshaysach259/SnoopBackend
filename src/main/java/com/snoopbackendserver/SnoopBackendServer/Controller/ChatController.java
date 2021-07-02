package com.snoopbackendserver.SnoopBackendServer.Controller;

import com.snoopbackendserver.SnoopBackendServer.Model.TextMessage;
import com.snoopbackendserver.SnoopBackendServer.Model.User;
import com.snoopbackendserver.SnoopBackendServer.Repositiories.UserJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@RestController
public class ChatController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private UserJPARepository userJPARepository;

    @PostMapping("/send")
    public ResponseEntity<Void> sendMessage(@RequestBody TextMessage message){
        System.out.println("================================================"+message.getMessage());
        messagingTemplate.convertAndSend("/topic/message",message);
        System.out.println("======================================Passed1");
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @SendTo("/topic/message")
    public TextMessage broadcastMessage(@Payload TextMessage message){
        message.setTimeStamp(LocalDateTime.now());
        return message;
    }
}
