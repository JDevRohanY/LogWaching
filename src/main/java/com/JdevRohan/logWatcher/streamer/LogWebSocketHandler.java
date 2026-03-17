package com.JdevRohan.logWatcher.streamer;

import com.JdevRohan.logWatcher.exceptions.LogWebSocketHandlerException;
import com.JdevRohan.logWatcher.services.LogReaderService;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class LogWebSocketHandler implements WebSocketHandler {
    Set<WebSocketSession> sessionSet = ConcurrentHashMap.newKeySet();
    private final LogReaderService logReaderService;

    public LogWebSocketHandler(LogReaderService logReaderService) {
        this.logReaderService = logReaderService;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessionSet.add(session);

        List<String> lastLogs = logReaderService.getLastNLines(10);

        for(String log : lastLogs){
            session.sendMessage(new TextMessage(log));
        }
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        //Nothing
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        //Nothing
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        sessionSet.remove(session);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    public void broadcast(String log){
        //It will broadcast the log to every open session
        for(WebSocketSession session : sessionSet){
            try{
                if(session.isOpen()){
                    session.sendMessage(new TextMessage(log));
                }
            }catch (Exception e){
                sessionSet.remove(session);
                throw new LogWebSocketHandlerException("Unable to broadcast the log. " + LogWebSocketHandler.class, e);
            }
        }
    }
}