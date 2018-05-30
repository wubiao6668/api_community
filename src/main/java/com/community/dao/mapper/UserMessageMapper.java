/**
 * Created by wubiao on - 2018/05/25.
 */

package com.community.dao.mapper;


import com.community.common.core.CurdAble;
import com.community.domain.model.db.UserMessageDO;
import com.community.domain.model.db.extend.UserMessageExtendDO;
import com.community.domain.request.UserMessageRequest;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMessageMapper extends CurdAble<Long, UserMessageRequest, UserMessageDO> {

    int insertSelectiveOrUpdateIfDuplicateKey(UserMessageExtendDO userMessageExtendDO);

    int updateExtSelective(UserMessageExtendDO userMessageExtendDO);

}
