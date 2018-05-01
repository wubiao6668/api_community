/**
 * Created by wubiao on - 2018/04/30.
 */

package com.community.dao.mapper;


import com.community.common.core.CurdAble;
import com.community.domain.model.db.ActivityMemberDO;
import com.community.domain.request.ActivityMemberRequest;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ActivityMemberMapper extends CurdAble<Long, ActivityMemberRequest, ActivityMemberDO> {

}
