package com.community.domain.session;

import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ApplicationSessionContext {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationSessionContext.class);
    //确保登录的时候已经初始化
    public static Map<Long, UserSession> userSessionMap = Maps.newConcurrentMap();

    public static void addWebSocketSession(Long userId, WebSocketSession webSocketSession) {
        UserSession userSession = userSessionMap.get(userId);
        if (null == userSession) {
            userSession = new UserSession(userId);
        }
        userSession.getSocketSessionMap().put(webSocketSession.getId(), webSocketSession);
        userSessionMap.put(userId, userSession);
    }

    /**
     * 根据userId获取用户信息
     *
     * @param userId
     * @return
     */
    public static Map<String, WebSocketSession> getWebSocketSessionByUserId(Long userId) {
        UserSession userSession = userSessionMap.get(userId);
        Map<String, WebSocketSession> webSocketSessionMap = null;
        if (null != userSession) {
            webSocketSessionMap = userSession.getSocketSessionMap();
        }
        return webSocketSessionMap;
    }

    /**
     * 根据userId获取用户信息 批量
     *
     * @param userIdSet
     * @return
     */
    public static Set<UserSession> getUserSessionByUserIds(Set<Long> userIdSet) {
        if (CollectionUtils.isEmpty(userIdSet)) {
            return null;
        }
        Set<UserSession> userSessionSet = userIdSet.parallelStream().map(aLong -> userSessionMap.get(aLong)).collect(Collectors.toSet());
        return userSessionSet;
    }

    public static boolean removeUserSessionByUserId(Long userId) {
        UserSession userSession = userSessionMap.get(userId);
        if (null != userSession) {
            Map<String, WebSocketSession> webSocketSessionMap = userSession.getSocketSessionMap();
            if (MapUtils.isNotEmpty(webSocketSessionMap)) {
                WebSocketSession webSocketSession = null;
                for (Map.Entry<String, WebSocketSession> webSocketSessionEntry : webSocketSessionMap.entrySet()) {
                    if (null != webSocketSessionEntry && null != (webSocketSession = webSocketSessionEntry.getValue())) {
                        try {
                            webSocketSession.close();
                        } catch (Exception e) {
                            LOGGER.error("method[removeUserSessionByUserId,]-userId:{},socketId:{},e:{}", userId, webSocketSession.getId(), e);
                        }
                    }
                }
            }
            userSession.setSocketSessionMap(null);
        }
        userSessionMap.remove(userId);
        return true;
    }

    public static boolean removeWebSocketFromUserSession(Long userId, String socketId) {
        UserSession userSession = userSessionMap.get(userId);
        if (null != userSession) {
            Map<String, WebSocketSession> webSocketSessionMap = userSession.getSocketSessionMap();
            if (MapUtils.isNotEmpty(webSocketSessionMap)) {
                webSocketSessionMap.remove(socketId);
            }
        }
        return true;
    }


}
