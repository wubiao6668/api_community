package com.community.domain.bo.websocket;

import com.community.domain.response.vo.UserInfoVO;
import lombok.Data;

import java.io.Serializable;

@Data
public class WsChannelGroupBO implements Serializable {
    private static final long serialVersionUID = 5569636063909935263L;

    private Long fromUserId;
    private Long toGroupId;
    private String message;

    private UserInfoVO fromUser;


}
