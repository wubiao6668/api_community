package com.community.domain.session;

import lombok.Data;
import org.springframework.web.socket.WebSocketSession;

@Data
public class UserSession implements UserDetail {
    /**
     * 用户ID
     */
    private long userId;
    /**
     * 姓名
     */
    private String userName;


    private final WebSocketSession session;

    public UserSession(WebSocketSession session) {
        this.session = session;
    }


}
