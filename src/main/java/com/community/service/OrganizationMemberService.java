/**
 * Created by wubiao on - 2018/04/30.
 */

package com.community.service;

import com.community.common.service.CurdServiceAble;
import com.community.domain.model.db.OrganizationMemberDO;
import com.community.domain.request.OrganizationMemberRequest;
import com.community.domain.response.OrganizationMemberResponse;

public interface OrganizationMemberService extends CurdServiceAble<Long, OrganizationMemberRequest, OrganizationMemberDO, OrganizationMemberResponse> {

}
