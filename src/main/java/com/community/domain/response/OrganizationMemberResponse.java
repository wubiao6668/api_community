/**
 * Created by wubiao on - 2018/04/30.
 */

package com.community.domain.response;

import com.community.common.util.DateUtils;
import com.community.domain.core.Page;
import com.community.domain.model.db.OrganizationMemberDO;
import com.community.domain.response.vo.OrganizationMemberVO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class OrganizationMemberResponse extends OrganizationMemberDO implements Serializable {
    private static final long serialVersionUID = 5454155825314635342L;

    private Page<List<OrganizationMemberVO>> orgMemberListPage;
    /**
     * 最后申请时间
     */
    private String applyLastTimeShow;
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

