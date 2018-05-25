/**
 * Created by wubiao on - 2018/05/25.
 */

package com.community.dao.mapper;


import com.community.common.core.CurdAble;
import com.community.domain.model.db.UserMessageDetailDO;
import com.community.domain.request.UserMessageDetailRequest;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMessageDetailMapper extends CurdAble<Long, UserMessageDetailRequest, UserMessageDetailDO> {

}
