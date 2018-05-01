/**
 * Created by wubiao on - 2018/04/29.
 */

package com.community.dao.mapper;


import com.community.common.core.CurdAble;
import com.community.domain.model.db.MessageSystemDetailDO;
import com.community.domain.request.MessageSystemDetailRequest;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MessageSystemDetailMapper extends CurdAble<Long, MessageSystemDetailRequest, MessageSystemDetailDO> {

}
