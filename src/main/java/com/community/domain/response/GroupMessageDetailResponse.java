/**
 * Created by wubiao on - 2018/05/25.
 */

package com.community.domain.response;

import com.community.common.util.DateUtils;
import com.community.domain.model.db.GroupMessageDetailDO;
import lombok.Data;

import java.io.Serializable;

@Data
public class GroupMessageDetailResponse extends GroupMessageDetailDO implements Serializable {
    private static final long serialVersionUID = 5454155825314635342L;

    /**
     * 消息时间
     */
    private String sendMsgTimeShow;
    /**
     * createTime
     */
    private String createTimeShow;
    /**
     * 修改时间
     */
    private String updateTimeShow;

    /**
     * 消息时间
     */
    public String getSendMsgTimeShow() {
        return DateUtils.dateShow(getSendMsgTime());
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

