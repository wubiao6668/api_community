package com.community.domain.response.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class MessageSystemDetailVO implements Serializable {

    /**
     * id
     */
    private Long id;
    /**
     * 系统消息表主键
     */
    private Long msgSysId;
    /**
     * 发送人
     */
    private Long senderUserId;
    /**
     * 接收人
     */
    private Long receiverUserId;
    /**
     * 状态（-1-未读、2-已读）
     */
    private Integer status;
    /**
     * 发送时间
     */
    private LocalDateTime sendTime;
    /**
     * 接收时间
     */
    private LocalDateTime receiveTime;
    /**
     * 消息表类型
     */
    private Integer msgType;
    /**
     * 具体类型
     */
    private Integer type;
    /**
     * 表主键
     */
    private Long bizId;

}
