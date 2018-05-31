package com.community.web.websocket.handler;

import com.community.common.constant.Constant;
import com.community.common.util.JacksonUtil;
import com.community.domain.bo.websocket.WsChannelBO;
import com.community.domain.bo.websocket.WsChannelFriendBO;
import com.community.domain.bo.websocket.WsChannelGroupBO;
import com.community.domain.session.ApplicationSessionContext;
import com.community.manager.WsChannelManager;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class WebSocketWsChannelHandler extends TextWebSocketHandler {

    @Autowired
    private WsChannelManager wsChannelManager;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        Long userId = (Long) session.getAttributes().get(Constant.USER_ID_KEY);
        ApplicationSessionContext.addWebSocketSession(userId, session);
    }


    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        if (StringUtils.isBlank(payload)) {
            return;
        }
        WsChannelBO wsChannelBO = JacksonUtil.parseObject(payload, WsChannelBO.class);
        if (null == wsChannelBO) {
            return;
        }
        //单人聊天
        if (Constant.WsChannelTypeEnum.FRIEND.getCode().equals(wsChannelBO.getType())) {
            WsChannelFriendBO wsChannelFriendBO = JacksonUtil.parseObject(wsChannelBO.getBody(), WsChannelFriendBO.class);
            wsChannelManager.sendToFriend(wsChannelFriendBO);
            return;
        }
        //群聊
        if (Constant.WsChannelTypeEnum.GROUP.getCode().equals(wsChannelBO.getType())) {
            WsChannelGroupBO wsChannelGroupBO = JacksonUtil.parseObject(wsChannelBO.getBody(), WsChannelGroupBO.class);
            wsChannelManager.sendToGroup(wsChannelGroupBO);
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        Long userId = (Long) session.getAttributes().get(Constant.USER_ID_KEY);
        ApplicationSessionContext.removeWebSocketFromUserSession(userId, session.getId());
    }
}
