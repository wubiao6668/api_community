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
public class OrganizationMemberDO implements Serializable {
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
     * 组织表主键
     */
    private Long orgId;
    /**
     * 状态（1-关注、2-取消关注3、拒绝加入）
     */
    private Integer status;
    /**
     * 申请次数
     */
    private Integer applyNum;
    /**
     * 最后申请时间
     */
    private LocalDateTime applyLastTime;
    /**
     * 加入理由
     */
    private String joinReason;
    /**
     * 操作人
     */
    private Long operatorId;
    /**
     * 角色（1-管理员、2-班委,3-普通用户）
     */
    private Integer role;
    /**
     * 角色别名
     */
    private String roleAlias;
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

