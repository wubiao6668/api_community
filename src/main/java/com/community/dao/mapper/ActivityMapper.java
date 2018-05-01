/**
 * Created by wubiao on - 2018/04/29.
 */

package com.community.dao.mapper;


import com.community.common.core.CurdAble;
import com.community.domain.model.db.ActivityDO;
import com.community.domain.request.ActivityRequest;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ActivityMapper extends CurdAble<Long, ActivityRequest, ActivityDO> {

}
