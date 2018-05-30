/**
 * Created by wubiao on - 2018/05/30.
 */

package com.community.domain.response;

import com.community.common.util.DateUtils;
import com.community.domain.model.db.UserChatInfoDO;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserChatInfoResponse extends UserChatInfoDO implements Serializable {
    private static final long serialVersionUID = 5454155825314635342L;

    /**
     * 最后发送时间
     */
    private String lastSendTimeShow;
    /**
     * createTime
     */
    private String createTimeShow;
    /**
     * 修改时间
     */
    private String updateTimeShow;

    /**
     * 最后发送时间
     */
    public String getLastSendTimeShow() {
        return DateUtils.dateShow(getLastSendTime());
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

