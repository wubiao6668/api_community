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
public class ActivityDO implements Serializable {
    private static final long serialVersionUID = 5454155825314635342L;
    /**
     * id
     */
    private Long id;
    /**
     * 发起人
     */
    private Long originatorUserId;
    /**
     * 活动名称
     */
    private String name;
    /**
     * 活动简介
     */
    private String introduction;
    /**
     * img
     */
    private String img;
    /**
     * 报名方式（1-不需要验证、2-需要验证、3-仅组织成员）
     */
    private Integer signUpWay;
    /**
     * 报名活动问题
     */
    private String signUpQuestion;
    /**
     * 发帖权限（1-不限、2-仅管理员、3-管理员、班委、4-管理员、班委、普通成员）
     */
    private Integer postPermission;
    /**
     * 纬度
     */
    private Long latitude;
    /**
     * 经度
     */
    private Long longitude;
    /**
     * 活动开始时间
     */
    private LocalDateTime startTime;
    /**
     * 活动结束时间
     */
    private LocalDateTime endTime;
    /**
     * 报名人数
     */
    private Integer signUpNum;
    /**
     * 帖子数
     */
    private Integer postNum;
    /**
     * 浏览数
     */
    private Integer viewNum;
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

