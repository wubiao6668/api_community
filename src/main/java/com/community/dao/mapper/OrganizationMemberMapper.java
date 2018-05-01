/**
 * Created by wubiao on - 2018/04/30.
 */

package com.community.dao.mapper;


import com.community.common.core.CurdAble;
import com.community.domain.model.db.OrganizationMemberDO;
import com.community.domain.request.OrganizationMemberRequest;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrganizationMemberMapper extends CurdAble<Long, OrganizationMemberRequest, OrganizationMemberDO> {

}
