/**
 * Created by wubiao on - 2018/04/29.
 */

package com.community.domain.response;

import com.community.common.util.DateUtils;
import com.community.domain.model.db.FollowUserDO;
import lombok.Data;

import java.io.Serializable;

@Data
public class FollowUserResponse extends FollowUserDO implements Serializable {
    private static final long serialVersionUID = 5454155825314635342L;

    /**
     * 关注时间
     */
    private String followTimeShow;
    /**
     * createTime
     */
    private String createTimeShow;
    /**
     * 修改时间
     */
    private String updateTimeShow;

    /**
     * 关注时间
     */
    public String getFollowTimeShow() {
        return DateUtils.dateShow(getFollowTime());
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

