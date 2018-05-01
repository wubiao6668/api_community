/**
 * Created by wubiao on - 2018/04/30.
 */

package com.community.domain.model.db;

import lombok.Data;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class ActivityMemberDO implements Serializable {
    private static final long serialVersionUID = 5454155825314635342L;
    /**
     * id
     */
    private Long id;
    /**
     * 成员user_id
     */
    private Long userId;
    /**
     * activity表主键
     */
    private Long actId;
    /**
     * 状态（-1审核不通过、1-审核中、2-成功报名,3-取消报名）
     */
    private Integer status;
    /**
     * 报名理由
     */
    private String signUpReason;
    /**
     * 申请次数
     */
    private Integer applyNum;
    /**
     * 最后申请时间
     */
    private LocalDateTime applyLastTime;
    /**
     * 操作人
     */
    private Long operatorId;
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

