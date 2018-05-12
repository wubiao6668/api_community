/**
 * Created by wubiao on - 2018/04/29.
 */

package com.community.domain.response;

import com.community.domain.core.Pagination;
import com.community.domain.response.vo.FavoriteVO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class FavoriteResponse implements Serializable {
    private static final long serialVersionUID = 5454155825314635342L;


    // 结果列表
    private List<FavoriteVO> favoriteList;
    //是否还有更多
    private Boolean hasMore;
    // 分页
    private Pagination pagination;

    @Override
    public String toString() {
        return super.toString();
    }
}

