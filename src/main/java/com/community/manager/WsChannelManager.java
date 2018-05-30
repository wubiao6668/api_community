package com.community.manager;

import com.community.common.constant.Constant;
import com.community.common.util.JacksonUtil;
import com.community.dao.mapper.UserChatInfoMapper;
import com.community.dao.mapper.UserChatMessageMapper;
import com.community.domain.bo.websocket.WsChannelFriendBO;
import com.community.domain.bo.websocket.WsChannelGroupBO;
import com.community.domain.model.db.UserChatMessageDO;
import com.community.domain.model.db.extend.UserChatInfoExtendDO;
import com.community.domain.response.vo.UserInfoVO;
import com.community.domain.session.ApplicationSessionContext;
import org.apache.commons.collections.MapUtils;
import org.elasticsearch.common.util.set.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

@Service
public class WsChannelManager {

    private static Logger logger = LoggerFactory.getLogger(WsChannelManager.class);

    @Autowired
    private UserChatInfoMapper userChatInfoMapper;
    @Autowired
    private UserChatMessageMapper userChatMessageMapper;
    @Autowired
    private UserInfoManager userInfoManager;

    public void sendToFriend(WsChannelFriendBO wsChannelBO) {
        Long fromUserId = wsChannelBO.getFromUserId();
        Long toUserId = wsChannelBO.getToUserId();

        //先入库
        boolean isFromUserLong = true;
        Long longUserId = fromUserId;
        Long shortUserId = toUserId;
        if (fromUserId < toUserId) {
            longUserId = toUserId;
            shortUserId = fromUserId;
            isFromUserLong = false;
        }
        UserChatInfoExtendDO userMessageExtendDO = new UserChatInfoExtendDO();
        userMessageExtendDO.setLongUserId(longUserId);
        userMessageExtendDO.setShortUserId(shortUserId);
        userMessageExtendDO.setLastSendTime(LocalDateTime.now());
        userMessageExtendDO.setContent(wsChannelBO.getMessage());
        userMessageExtendDO.setIsLongDelete(Constant.IsDeleteEnum.NO.getCode());
        userMessageExtendDO.setIsShortDelete(Constant.IsDeleteEnum.NO.getCode());
        if (isFromUserLong) {
            userMessageExtendDO.setShortUnReadMsgNumInc(1);
        } else {
            userMessageExtendDO.setLongUnReadMsgNumInc(1);
        }
        int row = userChatInfoMapper.insertSelectiveOrUpdateIfDuplicateKey(userMessageExtendDO);
        UserChatMessageDO userChatMessageDO = new UserChatMessageDO();
        int rowDetail = userChatMessageMapper.insertSelective(userChatMessageDO);
        //通知在线用户
        Map<String, WebSocketSession> webSocketSessionMap = ApplicationSessionContext.getWebSocketSessionByUserId(toUserId);
        if (MapUtils.isNotEmpty(webSocketSessionMap)) {
            //查询 用户信息
            Set<Long> idSet = Sets.newHashSet(wsChannelBO.getFromUserId(), wsChannelBO.getToUserId());
            Map<Long, UserInfoVO> userInfoVOMap = userInfoManager.getUserByIdIfNotExistReturnDefault(idSet);
            wsChannelBO.setFromUser(userInfoVOMap.get(wsChannelBO.getFromUserId()));
            wsChannelBO.setToUser(userInfoVOMap.get(wsChannelBO.getToUserId()));
            TextMessage textMessage = new TextMessage(JacksonUtil.toJSONString(wsChannelBO));
            for (WebSocketSession session : webSocketSessionMap.values()) {
                if (null != session) {
                    try {
                        session.sendMessage(textMessage);
                    } catch (Exception e) {
                        session = null;
                        webSocketSessionMap.remove(session.getId());
                        logger.error("method[sendToFriend]-e:{}", e);
                    }
                }
            }
        }
    }

    public void sendToGroup(WsChannelGroupBO wsChannelGroupBO) {
        //
    }


}
