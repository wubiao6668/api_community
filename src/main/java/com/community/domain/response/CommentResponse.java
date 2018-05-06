/**
 * Created by wubiao on - 2018/04/29.
 */

package com.community.domain.response;

import com.community.common.util.DateUtils;
import com.community.domain.model.db.CommentDO;
import com.community.domain.response.vo.CommentVO;
import lombok.Data;

import java.io.Serializable;

@Data
public class CommentResponse extends CommentDO implements Serializable {
    private static final long serialVersionUID = 5454155825314635342L;

    /**
     * 评论
     */
    private CommentVO comment;

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
    private UserInfoResponse userInfo;
    /**
     * 评论用户圈子角色
     */
    private OrganizationMemberResponse organizationMember;
    /**
     * 评论时间
     */
    private String commentTimeShow;
    /**
     * createTime
     */
    private String createTimeShow;
    /**
     * 修改时间
     */
    private String updateTimeShow;

    /**
     * 评论时间
     */
    public String getCommentTimeShow() {
        return DateUtils.dateShow(getCommentTime());
    }

    /**
     * createTime
     */
    public String getCreateTimeShow() {
        return DateUtils.dateShow(getCreateTime());
    }

    /**
     * 修改时间
     */
    public String getUpdateTimeShow() {
        return DateUtils.dateShow(getUpdateTime());
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

