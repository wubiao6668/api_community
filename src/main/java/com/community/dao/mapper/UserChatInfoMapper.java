/**
 * Created by wubiao on - 2018/05/30.
 */

package com.community.dao.mapper;


import com.community.common.core.CurdAble;
import com.community.domain.model.db.UserChatInfoDO;
import com.community.domain.request.UserChatInfoRequest;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserChatInfoMapper extends CurdAble<Long, UserChatInfoRequest, UserChatInfoDO> {

}
