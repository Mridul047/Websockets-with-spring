package com.mcy.websocketwithspring.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Component
public class AuctionMessageHandler extends TextWebSocketHandler {

    public List<WebSocketSession> webSocketSessions = Collections.synchronizedList(new ArrayList<>());

    public List<WebSocketSession> fetchSessions(){
        return webSocketSessions;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        System.out.println("afterConnection Established Session id:" + session.getId());
        webSocketSessions.add(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        System.out.println("afterConnection Closed Session id:" + session.getId());
        webSocketSessions.remove(session);
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {

        super.handleMessage(session, message);
        for (WebSocketSession webSocketSession : webSocketSessions) {
            System.out.println("handleMessage Session id:" + session.getId());
            webSocketSession.sendMessage(message);
        }
    }

}