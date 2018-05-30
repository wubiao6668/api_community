/**
 * Created by wubiao on - 2018/05/30.
 */

package com.community.dao.mapper;


import com.community.common.core.CurdAble;
import com.community.domain.model.db.GroupChatInfoDO;
import com.community.domain.request.GroupChatInfoRequest;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GroupChatInfoMapper extends CurdAble<Long, GroupChatInfoRequest, GroupChatInfoDO> {

}
