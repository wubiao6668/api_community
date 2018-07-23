package com.community.domain.session;

import com.community.common.constant.Constant;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;

public class ApplicationSessionContext {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationSessionContext.class);
    //确保登录的时候已经初始化
    public static Map<Long, UserSession> userSessionMap = Maps.newConcurrentMap();
    public static Map<String, WebSocketSession> webSocketSessionMap = Maps.newConcurrentMap();

    final static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

    final static ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
    final static ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();

    public static void addWebSocketSession(Long userId, WebSocketSession webSocketSession) {
        writeLock.lock();
        String webSocketSessionId = null;
        String webSocketSessionIdExist = null;
        try {
            UserSession userSession = userSessionMap.get(userId);
            if (null == userSession) {
                userSession = new UserSession(userId);
            }
            webSocketSessionId = webSocketSession.getId();
            Integer deviceType = (Integer) webSocketSession.getAttributes().get(Constant.DEVICE_TYPE);
            if (Constant.DEVICE_TYPE_APP.equals(deviceType)) {
                webSocketSessionIdExist = userSession.getSocketAppId();
                userSession.setSocketAppId(webSocketSessionId);
            } else if (Constant.DEVICE_TYPE_PC.equals(deviceType)) {
                webSocketSessionIdExist = userSession.getSocketPcId();
                userSession.setSocketPcId(webSocketSessionId);
            } else {
                webSocketSessionIdExist = userSession.getSocketWebId();
                userSession.setSocketWebId(webSocketSessionId);
            }
            userSessionMap.put(userId, userSession);
            webSocketSessionMap.put(webSocketSessionId, webSocketSession);
            if (StringUtils.isBlank(webSocketSessionIdExist)){
                WebSocketSession webSocketSessionExist = webSocketSessionMap.get(webSocketSessionIdExist);
                if (null == webSocketSessionExist && webSocketSessionExist.isOpen()){

                }
            }
        } catch (Exception e) {
            LOGGER.error("method[addWebSocketSession],userId:{},webSocketSessionId:{},webSocketSessionIdExist:{}", userId, webSocketSessionId, webSocketSessionIdExist);
        } finally {
            writeLock.unlock();
        }
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
//            webSocketSessionMap = userSession.getSocketSessionMap();
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
            Map<String, WebSocketSession> webSocketSessionMap = null;
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
//            userSession.setSocketSessionMap(null);
        }
        userSessionMap.remove(userId);
        return true;
    }

    public static boolean removeWebSocketFromUserSession(Long userId, String socketId) {
        UserSession userSession = userSessionMap.get(userId);
        if (null != userSession) {
            Map<String, WebSocketSession> webSocketSessionMap = null;
            if (MapUtils.isNotEmpty(webSocketSessionMap)) {
                webSocketSessionMap.remove(socketId);
            }
        }
        return true;
    }


}
