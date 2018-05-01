/**
 * Created by wubiao on - 2018/04/29.
 */

package com.community.domain.response;

import com.community.common.util.DateUtils;
import com.community.domain.model.db.UserInfoDO;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserInfoResponse extends UserInfoDO implements Serializable {
    private static final long serialVersionUID = 5454155825314635342L;

    /**
     * 上次登录时间
     */
    private String lastLoginTimeShow;
    /**
     * createTime
     */
    private String createTimeShow;
    /**
     * 修改时间
     */
    private String updateTimeShow;

    /**
     * 上次登录时间
     */
    public String getLastLoginTimeShow() {
        return DateUtils.dateShow(getLastLoginTime());
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

