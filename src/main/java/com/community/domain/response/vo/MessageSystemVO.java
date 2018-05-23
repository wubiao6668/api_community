package com.community.domain.response.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class MessageSystemVO implements Serializable {
    /**
     * id
     */
    private Long id;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 消息数
     */
    private Integer msgNum;
    /**
     * 消息类型（1-系统消息、2-点赞、2-评论、3-问答、4-活动）
     */
    private Integer type;
    /**
     * 是否展示（-1-不展示、1-展示）
     */
    private Integer isShow;
    /**
     * 是否已读（-1-未读、1-已读）
     */
    private Integer isRead;
    /**
     * 消息标题
     */
    private String title;
    /**
     * 消息内容
     */
    private String content;
    /**
     * 消息时间
     */
    private LocalDateTime sendMsgTime;

}
