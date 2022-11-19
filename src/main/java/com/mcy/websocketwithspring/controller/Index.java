package com.mcy.websocketwithspring.controller;

import com.mcy.websocketwithspring.handler.AuctionMessageHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.WebSocketSession;

import java.util.List;

@RestController
@RequestMapping("/api")
public class Index {
    private AuctionMessageHandler auctionMessageHandler;

    public Index(AuctionMessageHandler auctionMessageHandler){
        this.auctionMessageHandler = auctionMessageHandler;
    }

    @GetMapping("/currentSessions")
    public ResponseEntity<String> getSessions(){
        List<WebSocketSession> session =  auctionMessageHandler.fetchSessions();
       return ResponseEntity.ok("Current session count: " + String.valueOf(session.size()));
    }
}
