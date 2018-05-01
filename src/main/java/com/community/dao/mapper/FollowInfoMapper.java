/**
 * Created by wubiao on - 2018/04/29.
 */

package com.community.dao.mapper;


import com.community.common.core.CurdAble;
import com.community.domain.model.db.FollowInfoDO;
import com.community.domain.request.FollowInfoRequest;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FollowInfoMapper extends CurdAble<Long, FollowInfoRequest, FollowInfoDO> {

}
