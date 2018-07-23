package com.community.manager;

import com.community.common.constant.Constant;
import com.community.common.util.JacksonUtil;
import com.community.dao.mapper.*;
import com.community.domain.bo.AtInfoBO;
import com.community.domain.bo.websocket.WsChannelFriendBO;
import com.community.domain.bo.websocket.WsChannelGroupBO;
import com.community.domain.core.Page;
import com.community.domain.core.Response;
import com.community.domain.model.db.GroupChatMessageDO;
import com.community.domain.model.db.GroupMemberDO;
import com.community.domain.model.db.UserChatMessageDO;
import com.community.domain.model.db.extend.UserChatInfoExtendDO;
import com.community.domain.request.GroupMemberRequest;
import com.community.domain.response.vo.UserInfoVO;
import com.community.domain.session.ApplicationSessionContext;
import com.community.domain.session.UserSession;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.ArrayUtils;
import org.elasticsearch.common.util.set.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static com.community.common.constant.Constant.MAX_GROUP_MEMBER_NUM;

@Service
public class WsChannelManager {

    private static Logger logger = LoggerFactory.getLogger(WsChannelManager.class);

    @Autowired
    private UserChatInfoMapper userChatInfoMapper;
    @Autowired
    private UserChatMessageMapper userChatMessageMapper;
    @Autowired
    private UserInfoManager userInfoManager;
    @Autowired
    private GroupInfoMapper groupInfoMapper;
    @Autowired
    private GroupMemberMapper groupMemberMapper;
    @Autowired
    private GroupChatMessageMapper groupChatMessageMapper;

    /**
     * 两个用户之间发送信息
     *
     * @param wsChannelBO
     */
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
        UserSession userSession = ApplicationSessionContext.userSessionMap.get(toUserId);
//        if (null != userSession && MapUtils.isNotEmpty(userSession.getSocketSessionMap())) {
//            //查询 用户信息
//            Set<Long> idSet = Sets.newHashSet(wsChannelBO.getFromUserId(), wsChannelBO.getToUserId());
//            Map<Long, UserInfoVO> userInfoVOMap = userInfoManager.getUserByIdIfNotExistReturnDefault(idSet);
//            wsChannelBO.setFromUser(userInfoVOMap.get(wsChannelBO.getFromUserId()));
//            wsChannelBO.setToUser(userInfoVOMap.get(wsChannelBO.getToUserId()));
//            wsChannelBO.setId(wsChannelBO.getId());
//            TextMessage textMessage = new TextMessage(JacksonUtil.toJSONString(Response.success(wsChannelBO)));
//            sendMessage(userSession, textMessage);
//        }
    }

    /**
     * 群组发送信息
     *
     * @param wsChannelGroupBO
     */
    public void sendToGroup(WsChannelGroupBO wsChannelGroupBO) {
        Long groupId = wsChannelGroupBO.getToGroupId();
        Long fromUserId = wsChannelGroupBO.getFromUserId();
        Long[] atUserIds = wsChannelGroupBO.getAtUserIds();
        //先入库
        GroupChatMessageDO groupChatMessageDO = new GroupChatMessageDO();
        groupChatMessageDO.setGroupId(groupId);
        groupChatMessageDO.setSenderUserId(fromUserId);
        groupChatMessageDO.setType(wsChannelGroupBO.getType());
        //是否at用户
        if (ArrayUtils.isNotEmpty(atUserIds)) {
            List<AtInfoBO> atInfoBOList = Lists.newArrayList();
            for (Long atUserId : atUserIds) {
                atInfoBOList.add(new AtInfoBO(atUserId));
            }
            AtInfoBO[] atInfo = atInfoBOList.toArray(new AtInfoBO[]{});
            groupChatMessageDO.setAtInfo(atInfo);
        }
        groupChatMessageDO.setIsAnonymous(wsChannelGroupBO.getIsAnonymous());
        groupChatMessageDO.setContent(wsChannelGroupBO.getMessage());
        groupChatMessageDO.setSendMsgTime(LocalDateTime.now());
        groupChatMessageDO.setCreateUserId(fromUserId);
        int row = groupChatMessageMapper.insertSelective(groupChatMessageDO);
        //查询群用户
        GroupMemberRequest groupMemberRequest = new GroupMemberRequest();
        groupMemberRequest.setGroupId(groupId);
        groupMemberRequest.getPagination().setLimitPageSize(MAX_GROUP_MEMBER_NUM);
        Page<GroupMemberDO> memberDOPage = groupMemberMapper.listPage(groupMemberRequest);
        List<GroupMemberDO> groupMemberDOList = Optional.ofNullable(memberDOPage).flatMap(Page::getData).orElse(null);
        if (CollectionUtils.isNotEmpty(groupMemberDOList)) {
            Set<Long> memberUserIdSet = groupMemberDOList.stream().map(groupMemberDO -> groupMemberDO.getUserId()).collect(Collectors.toSet());
            Set<UserSession> userSessionSet = ApplicationSessionContext.getUserSessionByUserIds(memberUserIdSet);
            TextMessage textMessage = new TextMessage(JacksonUtil.toJSONString(Response.success(wsChannelGroupBO)));
            userSessionSet.forEach(userSession -> {
                sendMessageSync(userSession, textMessage);
            });
        }
    }

    /**
     * 异步发送信息
     *
     * @param userSession
     * @param textMessage
     */
    public void sendMessageSync(UserSession userSession, TextMessage textMessage) {
//        CompletableFuture.runAsync(() -> sendMessage(userSession, textMessage));
    }

    /**
     * 发送信息
     *
     * @param webSocketSessionList
     * @param textMessage
     */
    public void sendMessage(List<WebSocketSession> webSocketSessionList, TextMessage textMessage) {
        if (CollectionUtils.isNotEmpty(webSocketSessionList)){
            return;
        }

        for (WebSocketSession session : webSocketSessionList) {
            if (null != session) {
                try {
                    session.sendMessage(textMessage);
                } catch (Exception e) {
//                    webSocketSessionMap.remove(session.getId());
                    logger.error("method[sendMessage]-e:{}", e);
                }
            }
        }
    }


}
