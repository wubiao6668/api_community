/**
 * Created by wubiao on - 2018/04/29.
 */

package com.community.dao.mapper;


import com.community.common.core.CurdAble;
import com.community.domain.model.db.MessageSystemDO;
import com.community.domain.request.MessageSystemRequest;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MessageSystemMapper extends CurdAble<Long, MessageSystemRequest, MessageSystemDO> {

}
