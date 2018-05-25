/**
 * Created by wubiao on - 2018/05/25.
 */

package com.community.domain.model.db;

import lombok.Data;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class GroupMessageDetailDO implements Serializable {
    private static final long serialVersionUID = 5454155825314635342L;
    /**
     * id
     */
    private Long id;
    /**
     * group_info 表id
     */
    private Long groupId;
    /**
     * 发送人
     */
    private Long senderUserId;
    /**
     * @信息 json
     */
    private String atInfo;
    /**
     * 消息类型（1-系统消息、2-用户）
     */
    private Integer type;
    /**
     * 是否匿名（-1-是、1-否）
     */
    private Integer isAnonymous;
    /**
     * 消息内容
     */
    private String content;
    /**
     * 消息时间
     */
    private LocalDateTime sendMsgTime;
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

