/**
 * Created by wubiao on - 2018/04/29.
 */

package com.community.dao.mapper;


import com.community.common.core.CurdAble;
import com.community.domain.model.db.LikeDO;
import com.community.domain.request.LikeRequest;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface LikeMapper extends CurdAble<Long, LikeRequest, LikeDO> {

    /**
     * 查询userId是否点赞了
     *
     * @param request
     * @return
     */
    @MapKey("user_id")
    Map<Long, LikeDO> getByBizIdUserIdAndType(LikeRequest request);
}
