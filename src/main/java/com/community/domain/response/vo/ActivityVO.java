package com.community.domain.response.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class ActivityVO implements Serializable {
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
}
