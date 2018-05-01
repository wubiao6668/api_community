/**
 * Created by wubiao on - 2018/04/29.
 */

package com.community.domain.response;

import com.community.common.util.DateUtils;
import com.community.domain.model.db.ActivityDO;
import lombok.Data;

import java.io.Serializable;

@Data
public class ActivityResponse extends ActivityDO implements Serializable {
    private static final long serialVersionUID = 5454155825314635342L;

    /**
     * 活动开始时间
     */
    private String startTimeShow;
    /**
     * 活动结束时间
     */
    private String endTimeShow;
    /**
     * createTime
     */
    private String createTimeShow;
    /**
     * 修改时间
     */
    private String updateTimeShow;

    /**
     * 活动开始时间
     */
    public String getStartTimeShow() {
        return DateUtils.dateShow(getStartTime());
    }

    /**
     * 活动结束时间
     */
    public String getEndTimeShow() {
        return DateUtils.dateShow(getEndTime());
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

