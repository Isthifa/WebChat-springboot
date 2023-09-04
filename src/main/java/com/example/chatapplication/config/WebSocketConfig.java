package com.example.chatapplication.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker // Enables WebSocket message handling, backed by a message broker.
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").setAllowedOriginPatterns("*").withSockJS(); // Registers the "/chat" endpoint, enabling SockJS fallback options so that alternate transports may be used if WebSocket is not available. The SockJS client will attempt to connect to "/chat" and use the best available transport (websocket, xhr-streaming, xhr-polling, and so on).
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app"); // Designates the "/app" prefix for messages that are bound for methods annotated with @MessageMapping.
        registry.enableSimpleBroker("/chatroom","/user"); // Designates the "/topic" prefix for messages that are broadcast to all connected clients.
        registry.setUserDestinationPrefix("/user"); // Designates the "/user" prefix for messages that are bound for methods annotated with @MessageMapping.
        }

}
