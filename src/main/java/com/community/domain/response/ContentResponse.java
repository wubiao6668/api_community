/**
 * Created by wubiao on - 2018/04/30.
 */

package com.community.domain.response;

import com.community.common.util.DateUtils;
import com.community.domain.bo.ContentBO;
import com.community.domain.model.db.ContentDO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ContentResponse extends ContentDO implements Serializable {
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
     * 摘要
     */
    private String summary;
    /**
     * contentJson 转成 对象
     */
    private List<ContentBO> contentList;
    /**
     * 组织
     */
    private OrganizationResponse organization;
    /**
     * 活动
     */
    private ActivityResponse activity;
    /**
     * 发帖人
     */
    private UserInfoResponse userInfo;

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

