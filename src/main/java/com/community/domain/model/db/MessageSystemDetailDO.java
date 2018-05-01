/**
 * Created by wubiao on - 2018/04/29.
 */

package com.community.domain.model.db;

import lombok.Data;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class MessageSystemDetailDO implements Serializable {
    private static final long serialVersionUID = 5454155825314635342L;
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
     * 消息类型100-系统（101-系统）200-点赞（1-组织内容、2-活动内容、3-问题内容、4-回答内容、5-评论、6-回复）、300-评论回复（1-评论回答、2-评论帖子、3-回复评论、4-回复回复）、400-问答（1-回答问题）、500-活动（1-活动报名）
     */
    private Integer type;
    /**
     * 表主键
     */
    private Long bizId;
    /**
     * 是否删除（0-未删除、1-已删除）
     */
    private Integer isDelete;
    /**
     * 创建者user_id
     */
    private Long createUserId;
    /**
     * createTime
     */
    private LocalDateTime createTime;
    /**
     * 修改人user_id
     */
    private Long updateUserId;
    /**
     * 修改时间
     */
    private LocalDateTime updateTime;
    /**
     * 版本号
     */
    private Integer sysVersion;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}

