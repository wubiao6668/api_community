/**
 * Created by wubiao on - 2018/05/30.
 */

package com.community.domain.model.db;

import lombok.Data;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class UserChatInfoDO implements Serializable {
    private static final long serialVersionUID = 5454155825314635342L;
    /**
     * id
     */
    private Long id;
    /**
     * 未读消息数
     */
    private Integer shortUnReadMsgNum;
    /**
     * 未读消息数
     */
    private Integer longUnReadMsgNum;
    /**
     * 长userId
     */
    private Long shortUserId;
    /**
     * 短userId
     */
    private Long longUserId;
    /**
     * 最后发送时间
     */
    private LocalDateTime lastSendTime;
    /**
     * 消息内容
     */
    private String content;
    /**
     * 是否删除（0-未删除、1-已删除）
     */
    private Integer isShortDelete;
    /**
     * 是否删除（0-未删除、1-已删除）
     */
    private Integer isLongDelete;
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

