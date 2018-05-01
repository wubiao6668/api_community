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
public class ContentDO implements Serializable {
    private static final long serialVersionUID = 5454155825314635342L;
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
    private String contentJson;
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
    private String tagExtend;
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

