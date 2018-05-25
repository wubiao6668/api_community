/**
 * Created by wubiao on - 2018/05/25.
 */

package com.community.domain.response;

import com.community.common.util.DateUtils;
import com.community.domain.model.db.GroupMemberDO;
import lombok.Data;

import java.io.Serializable;

@Data
public class GroupMemberResponse extends GroupMemberDO implements Serializable {
    private static final long serialVersionUID = 5454155825314635342L;

    /**
     * 最后申请时间
     */
    private String applyLastTimeShow;
    /**
     * 禁言过期时间
     */
    private String interdictionExpiryTimeShow;
    /**
     * createTime
     */
    private String createTimeShow;
    /**
     * 修改时间
     */
    private String updateTimeShow;

    /**
     * 最后申请时间
     */
    public String getApplyLastTimeShow() {
        return DateUtils.dateShow(getApplyLastTime());
    }

    /**
     * 禁言过期时间
     */
    public String getInterdictionExpiryTimeShow() {
        return DateUtils.dateShow(getInterdictionExpiryTime());
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

