package com.bezkoder.spring.datajpa.configurations;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class WebSocketEventListener {
    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        // Get the username from the session
        String username = event.getUser().getName();
        // Set user online status to true
        // Update user online status in your database or user repository
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        // Get the username from the session
        String username = event.getUser().getName();
        // Set user online status to false
        // Update user online status in your database or user repository
    }
}
