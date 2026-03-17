package com.JdevRohan.logWatcher.config;

import com.JdevRohan.logWatcher.streamer.LogWebSocketHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
public class WebSocketConfig implements WebSocketConfigurer {
    //Need a handler
    private final LogWebSocketHandler handler;

    public WebSocketConfig(LogWebSocketHandler handler) {
        this.handler = handler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(handler, "/logs")
                .setAllowedOrigins("*");
    }
}
