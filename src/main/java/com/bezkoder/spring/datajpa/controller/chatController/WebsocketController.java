package com.bezkoder.spring.datajpa.controller.chatController;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class WebsocketController {

    private final  SimpMessagingTemplate messagingTemplate;

    public WebsocketController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/send/message")
    public void sendMessage(@Payload String message) {
        // Broadcast the received message to all subscribers of /topic/messages
        messagingTemplate.convertAndSend("/topic/messages", message);
    }

    @MessageMapping("/send/notification")
    public void sendNotification(@Payload String notification) {
        // Process the notification and send it to the appropriate client
        this.messagingTemplate.convertAndSend("/topic/notifications", notification);
    }
}
