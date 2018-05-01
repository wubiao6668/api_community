/**
 * Created by wubiao on - 2018/05/01.
 */

package com.community.dao.mapper;


import com.community.common.core.CurdAble;
import com.community.domain.model.db.UserInfoDO;
import com.community.domain.request.UserInfoRequest;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserInfoMapper extends CurdAble<Long, UserInfoRequest, UserInfoDO> {

}
