package com.community.domain.response.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class CommentVO implements Serializable {
    /**
     * id
     */
    private Long id;
    /**
     * 评论用户
     */
    private Long userId;
    /**
     * 类型（1-回答、2-帖子）
     */
    private Integer type;
    /**
     * 表主键id
     */
    private Long bizId;
    /**
     * 评论时间
     */
    private LocalDateTime commentTime;
    /**
     * 状态(-1-屏蔽、1-审核中、2-审核通过、3-精选)
     */
    private Integer status;
    /**
     * 置顶（0-正常、1-置顶）
     */
    private Integer top;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 点赞数
     */
    private Integer likeNum;
    /**
     * 回复数
     */
    private Integer replyNum;
    /**
     * 是否点赞
     */
    private Boolean isLike;
    /**
     * 是否自己的
     */
    private Boolean isOwn;
    /**
     * 评论用户
     */
    private UserInfoVO userInfo;
    /**
     * 点赞用户
     */
    private List<UserInfoVO> likeUserInfo;

}
