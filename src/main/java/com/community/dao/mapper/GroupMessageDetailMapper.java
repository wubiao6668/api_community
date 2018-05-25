/**
 * Created by wubiao on - 2018/05/25.
 */

package com.community.dao.mapper;


import com.community.common.core.CurdAble;
import com.community.domain.model.db.GroupMessageDetailDO;
import com.community.domain.request.GroupMessageDetailRequest;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GroupMessageDetailMapper extends CurdAble<Long, GroupMessageDetailRequest, GroupMessageDetailDO> {

}
