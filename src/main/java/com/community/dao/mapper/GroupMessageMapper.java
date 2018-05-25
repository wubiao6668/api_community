/**
 * Created by wubiao on - 2018/05/25.
 */

package com.community.dao.mapper;


import com.community.common.core.CurdAble;
import com.community.domain.model.db.GroupMessageDO;
import com.community.domain.request.GroupMessageRequest;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GroupMessageMapper extends CurdAble<Long, GroupMessageRequest, GroupMessageDO> {

}
