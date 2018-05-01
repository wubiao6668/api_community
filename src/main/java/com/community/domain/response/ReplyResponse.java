/**
 * Created by wubiao on - 2018/04/29.
 */

package com.community.domain.response;

import com.community.common.util.DateUtils;
import com.community.domain.model.db.ReplyDO;
import lombok.Data;

import java.io.Serializable;

@Data
public class ReplyResponse extends ReplyDO implements Serializable {
    private static final long serialVersionUID = 5454155825314635342L;

    /**
     * 回复时间
     */
    private String replyTimeShow;
    /**
     * createTime
     */
    private String createTimeShow;
    /**
     * 修改时间
     */
    private String updateTimeShow;

    /**
     * 回复时间
     */
    public String getReplyTimeShow() {
        return DateUtils.dateShow(getReplyTime());
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

