/**
 * Created by wubiao on - 2018/04/29.
 */

package com.community.dao.mapper;


import com.community.common.core.CurdAble;
import com.community.domain.model.db.LikeDO;
import com.community.domain.request.LikeRequest;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LikeMapper extends CurdAble<Long, LikeRequest, LikeDO> {

}
