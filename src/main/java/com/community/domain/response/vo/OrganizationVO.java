package com.community.domain.response.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrganizationVO implements Serializable {
    private static final long serialVersionUID = -8902643684605706106L;

    /**
     * 主键自增
     */
    private Long id;
    /**
     * 组织名称
     */
    private String name;
    /**
     * 简介
     */
    private String introduction;
    /**
     * 图片
     */
    private String img;
    /**
     * 发起人
     */
    private Long originatorUserId;
    /**
     * 类型（1-官方、2-用户自建）
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
     * 发帖权限（1-不限、2-仅管理员、3-管理员、班委、4-管理员、班委、普通成员）
     */
    private Integer postPermission;
    /**
     * 加入组织问题
     */
    private String joinQuestion;
    /**
     * 帖子数
     */
    private Integer postNum;
    /**
     * 关注数
     */
    private Integer followerNum;
    /**
     * 浏览数
     */
    private Integer viewNum;
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
     * 是否关注
     */
    private Boolean isFollow;
    /**
     * 角色
     */
    private Integer role;


}
