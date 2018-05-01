/**
 * Created by wubiao on - 2018/04/29.
 */

package com.community.dao.mapper;


import com.community.common.core.CurdAble;
import com.community.domain.model.db.OrganizationDO;
import com.community.domain.request.OrganizationRequest;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrganizationMapper extends CurdAble<Long, OrganizationRequest, OrganizationDO> {

}
