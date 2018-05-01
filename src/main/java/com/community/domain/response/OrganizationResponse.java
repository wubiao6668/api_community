/**
 * Created by wubiao on - 2018/04/29.
 */

package com.community.domain.response;

import com.community.common.util.DateUtils;
import com.community.domain.model.db.OrganizationDO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class OrganizationResponse extends OrganizationDO implements Serializable {
    private static final long serialVersionUID = 5454155825314635342L;

    /**
     * createTime
     */
    private String createTimeShow;
    /**
     * 修改时间
     */
    private String updateTimeShow;
    /**
     * 帖子
     */
    private List<ContentResponse> contentList;
    /**
     * 活动
     */
    private List<ActivityResponse> activityList;
    /**
     * 标签
     */
    private List<TagResponse> tagList;
    /**
     *
     */
    private OrganizationMemberResponse organizationMember;

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

