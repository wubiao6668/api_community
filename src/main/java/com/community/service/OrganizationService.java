/**
 * Created by wubiao on - 2018/04/29.
 */

package com.community.service;

import com.community.common.service.CurdServiceAble;
import com.community.domain.model.db.OrganizationDO;
import com.community.domain.request.OrganizationRequest;
import com.community.domain.response.OrganizationResponse;

public interface OrganizationService extends CurdServiceAble<Long, OrganizationRequest, OrganizationDO, OrganizationResponse> {

}
