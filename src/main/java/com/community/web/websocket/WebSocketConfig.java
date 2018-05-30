package com.community.web.websocket;

import com.community.web.websocket.handler.WebSocketWsChannelHandler;
import com.community.web.websocket.interceptors.HandshakeWsChannelInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new WebSocketWsChannelHandler(), "/ws/channel")
                .addInterceptors(new HandshakeWsChannelInterceptor()).withSockJS();
    }
}
