/**
 * Created by wubiao on - 2018/05/30.
 */

package com.community.dao.mapper;


import com.community.common.core.CurdAble;
import com.community.domain.model.db.UserChatMessageDO;
import com.community.domain.request.UserChatMessageRequest;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserChatMessageMapper extends CurdAble<Long, UserChatMessageRequest, UserChatMessageDO> {

}
