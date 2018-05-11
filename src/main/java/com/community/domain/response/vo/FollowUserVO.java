package com.community.domain.response.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class FollowUserVO implements Serializable {
    /**
     * id
     */
    private Long id;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 关注用户id
     */
    private Long followUserId;
    /**
     * 关注时间
     */
    private LocalDateTime followTime;
    /**
     * 关注状态（-1拉黑、1-关注、2-取消关注）
     */
    private Integer status;

    /**
     * 关注用户信息
     */
    private UserInfoVO followUserInfo;
    /**
     * 是否相互关注
     */
    private boolean isFollowEachOther;
}
