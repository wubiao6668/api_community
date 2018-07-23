package com.community.domain.session;

import com.google.common.collect.Maps;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;

@Data
@NoArgsConstructor
public class UserSession implements UserDetail {
    /**
     * 用户ID
     */
    private long userId;
    /**
     * 姓名
     */
    private String userName;

    public UserSession(long userId) {
        this.userId = userId;
    }

    private String socketWebId;
    private String socketAppId;
    private String socketPcId;

//    private Map<String,WebSocketSession> socketSessionMap = Maps.newConcurrentMap();
//
//    public long getUserId() {
//        return userId;
//    }
//
//    public void setUserId(long userId) {
//        this.userId = userId;
//    }
//
//    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }

//    public Map<String, WebSocketSession> getSocketSessionMap() {
//        return socketSessionMap;
//    }
//
//    public void setSocketSessionMap(Map<String, WebSocketSession> socketSessionMap) {
//        this.socketSessionMap = socketSessionMap;
//    }
}
