/**
 * Created by wubiao on - 2018/05/01.
 */

package com.community.domain.request;

import com.community.common.constant.Constant;
import com.community.domain.core.PageAble;
import com.community.domain.core.PaginationAble;
import com.community.domain.core.SortAble;
import com.community.domain.model.db.UserInfoDO;
import com.google.common.collect.Lists;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class UserInfoRequest extends UserInfoDO implements PageAble, Serializable {
    private static final long serialVersionUID = 3148176768559230877L;

    /**
     * 上次登录时间
     */
    private LocalDateTime lastLoginTimeBegin;
    private LocalDateTime lastLoginTimeEnd;
    /**
     * createTime
     */
    private LocalDateTime createTimeBegin;
    private LocalDateTime createTimeEnd;
    /**
     * 修改时间
     */
    private LocalDateTime updateTimeBegin;
    private LocalDateTime updateTimeEnd;

    private PaginationAble pagination = new PaginationAble();

    private List<SortAble> sortList = Lists.newArrayList(new SortAble(Constant.SortFieldEnum.ID.getCode(), Constant.SortOrderEnum.DESC.getCode()));

    @Override
    public String toString() {
        return super.toString();
    }
}

