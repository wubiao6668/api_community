/**
 * Created by wubiao on - 2018/04/29.
 */

package com.community.dao.mapper;


import com.community.common.core.CurdAble;
import com.community.domain.model.db.FollowUserDO;
import com.community.domain.request.FollowUserRequest;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FollowUserMapper extends CurdAble<Long, FollowUserRequest, FollowUserDO> {

}
