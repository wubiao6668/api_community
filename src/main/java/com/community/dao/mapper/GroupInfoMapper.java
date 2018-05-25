/**
 * Created by wubiao on - 2018/05/25.
 */

package com.community.dao.mapper;


import com.community.common.core.CurdAble;
import com.community.domain.model.db.GroupInfoDO;
import com.community.domain.request.GroupInfoRequest;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GroupInfoMapper extends CurdAble<Long, GroupInfoRequest, GroupInfoDO> {

}
