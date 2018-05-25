/**
 * Created by wubiao on - 2018/05/25.
 */

package com.community.dao.mapper;


import com.community.common.core.CurdAble;
import com.community.domain.model.db.GroupMemberDO;
import com.community.domain.request.GroupMemberRequest;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GroupMemberMapper extends CurdAble<Long, GroupMemberRequest, GroupMemberDO> {

}
