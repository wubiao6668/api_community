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
public class GroupInfoDO implements Serializable {
    private static final long serialVersionUID = 5454155825314635342L;
    /**
     * id
     */
    private Long id;
    /**
     * 组织名称
     */
    private String name;
    /**
     * 介绍
     */
    private String introduction;
    /**
     * 公告
     */
    private String notice;
    /**
     * 图片
     */
    private String img;
    /**
     * 发起人
     */
    private Long originatorUserId;
    /**
     * 类型（1-组织、2-活动、3-分类）
     */
    private Integer type;
    /**
     * 状态（-1-驳回、1-待审核 、2-审核通过）
     */
    private Integer status;
    /**
     * 加入方式（1-不需要验证、2-需要验证）
     */
    private Integer joinWay;
    /**
     * 发言权限（1-不限、2-仅管理员、3-管理员、班委、4-管理员、班委、普通成员）
     */
    private Integer speakPermission;
    /**
     * 加入组织问题
     */
    private String joinQuestion;
    /**
     * 成员数
     */
    private Integer memberNum;
    /**
     * 纬度
     */
    private Double latitude;
    /**
     * 经度
     */
    private Double longitude;
    /**
     * 城市
     */
    private Long cityId;
    /**
     * 分类ID、活动ID、组织ID
     */
    private Long bizId;
    /**
     * 群人数上限
     */
    private Integer memberNumLimit;
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

