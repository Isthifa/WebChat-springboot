package com.example.chatapplication.controller;


import com.example.chatapplication.Util.ChatMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class ChatController {

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @MessageMapping("/message") // The @MessageMapping annotation ensures that, if a message is sent to the /chat.sendMessage destination, the sendMessage() method is called.
    @SendTo("/chatroom/public") // The return value is broadcast to all subscribers of /topic/public, as specified in the @SendTo annotation.
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        return chatMessage;
    }

    @MessageMapping("/private-message") // The @MessageMapping annotation ensures that, if a message is sent to the /chat.addUser destination, the addUser() method is called.
    //@SendTo("/topic/public") // The return value is broadcast to all subscribers of /topic/public, as specified in the @SendTo annotation.

    public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) { // SimpMessageHeaderAccessor is used to retrieve the username from the message header.
//        headerAccessor.getSessionAttributes().put("username", chatMessage.getSenderName()); // Add username in web socket session
        messagingTemplate.convertAndSendToUser(chatMessage.getReceiverName(),"/private",chatMessage);
        log.info(chatMessage.getReceiverName(),chatMessage);
        return chatMessage;
    }
}
