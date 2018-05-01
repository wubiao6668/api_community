/**
 * Created by wubiao on - 2018/04/29.
 */

package com.community.domain.response;

import com.community.common.util.DateUtils;
import com.community.domain.model.db.MessageSystemDetailDO;
import lombok.Data;

import java.io.Serializable;

@Data
public class MessageSystemDetailResponse extends MessageSystemDetailDO implements Serializable {
    private static final long serialVersionUID = 5454155825314635342L;

    /**
     * 发送时间
     */
    private String sendTimeShow;
    /**
     * 接收时间
     */
    private String receiveTimeShow;
    /**
     * createTime
     */
    private String createTimeShow;
    /**
     * 修改时间
     */
    private String updateTimeShow;

    /**
     * 发送时间
     */
    public String getSendTimeShow() {
        return DateUtils.dateShow(getSendTime());
    }

    /**
     * 接收时间
     */
    public String getReceiveTimeShow() {
        return DateUtils.dateShow(getReceiveTime());
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

