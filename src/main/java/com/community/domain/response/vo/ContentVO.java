package com.community.domain.response.vo;

import com.community.domain.bo.ContentBO;
import com.community.domain.bo.TagBO;
import lombok.Data;

import java.io.Serializable;

@Data
public class ContentVO implements Serializable {

    /**
     * id
     */
    private Long id;
    /**
     * 发表人
     */
    private Long userId;
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private ContentBO[] contents;
    /**
     * 纬度
     */
    private Long latitude;
    /**
     * 经度
     */
    private Long longitude;
    /**
     * 类型（1-组织帖子内容、2-活动帖子内容、3-问题内容、4、回答内容）
     */
    private Integer type;
    /**
     * 组织、问题id
     */
    private Long bizId;
    /**
     * 活动id
     */
    private Long bizChildId;
    /**
     * 状态（-1-屏蔽、1-审核中、2-审核通过、3-加精）
     */
    private Integer status;
    /**
     * 置顶（0-正常、1-置顶）
     */
    private Integer top;
    /**
     * 图片个数
     */
    private Integer imgNum;
    /**
     * 评论数、(问题)回答数
     */
    private Integer commentNum;
    /**
     * 浏览数
     */
    private Integer viewNum;
    /**
     * 点赞、有用数
     */
    private Integer likeNum;
    /**
     * 收藏数
     */
    private Integer favoriteNum;
    /**
     * 关注数
     */
    private Integer followNum;
    /**
     * 标签tag表主键
     */
    private Long tagId;
    /**
     * 自定义标签
     */
    private TagBO[] tagExtend;

    /**
     * 摘要
     */
    private String summary;

    /**
     * 用户
     */
    private UserInfoVO userInfo;
    /**
     * 组织
     */
    private OrganizationVO organization;
    /**
     * 活动
     */
    private ActivityVO activity;
    /**
     * 组织成员
     */
    private OrganizationMemberVO organizationMember;
    /**
     * 问题
     */
    private ContentVO questionContent;

}
