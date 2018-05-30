/**
 * Created by wubiao on - 2018/05/30.
 */

package com.community.dao.mapper;


import com.community.common.core.CurdAble;
import com.community.domain.model.db.GroupChatMessageDO;
import com.community.domain.request.GroupChatMessageRequest;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GroupChatMessageMapper extends CurdAble<Long, GroupChatMessageRequest, GroupChatMessageDO> {

}
