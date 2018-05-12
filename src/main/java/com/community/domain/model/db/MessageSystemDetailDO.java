/**
 * Created by wubiao on - 2018/05/12.
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

