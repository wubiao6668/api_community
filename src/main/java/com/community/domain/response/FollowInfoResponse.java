/**
 * Created by wubiao on - 2018/04/29.
 */

package com.community.domain.response;

import com.community.domain.core.Pagination;
import com.community.domain.response.vo.FollowInfoVO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class FollowInfoResponse implements Serializable {
    private static final long serialVersionUID = 5454155825314635342L;

    private Boolean hasMore;
    // 分页
    private Pagination pagination;
    // 结果列表
    private List<FollowInfoVO> followInfoList;

    @Override
    public String toString() {
        return super.toString();
    }
}

