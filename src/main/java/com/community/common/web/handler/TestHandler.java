package com.community.common.web.handler;

import com.google.common.collect.Lists;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class TestHandler extends TextWebSocketHandler {

    public static  List<WebSocketSession> socketSessionList = Lists.newArrayList();


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        socketSessionList.add(session);
        super.afterConnectionEstablished(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
    }
}
