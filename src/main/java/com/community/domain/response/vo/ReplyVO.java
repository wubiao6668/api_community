package com.community.domain.response.vo;

import com.community.common.util.DateUtils;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class ReplyVO implements Serializable {
    private static final long serialVersionUID = 6416423146916285441L;

    /**
     * id
     */
    private Long id;
    /**
     * 评论用户
     */
    private Long userId;
    /**
     * 类型（1-评论、2-回复）
     */
    private Integer type;
    /**
     * 评论id
     */
    private Long commentId;
    /**
     * 回复id
     */
    private Long replyId;
    /**
     * 回复时间
     */
    private LocalDateTime replyTime;
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
     * 评论用户
     */
    private UserInfoVO userInfo;
    /**
     * 回复主题
     */
    private ReplyVO replyInfo;

    /**
     * 回复时间
     */
    public String getReplyTimeShow() {
        return DateUtils.dateShow(getReplyTime());
    }
}
