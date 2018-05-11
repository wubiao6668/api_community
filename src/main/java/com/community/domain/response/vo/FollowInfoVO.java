package com.community.domain.response.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class FollowInfoVO implements Serializable {
    private static final long serialVersionUID = -5656593761605557700L;

    /**
     * id
     */
    private Long id;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 关注对象主键(问题等)
     */
    private Long bizId;
    /**
     * 类型（1-问题）
     */
    private Integer type;
    /**
     * 状态（1-关注、2-取消关注）
     */
    private Integer status;
    /**
     * followTime
     */
    private LocalDateTime followTime;
    /**
     * 关注的问题
     */
    private ContentVO questionContent;

}
