/**
 * Created by wubiao on - 2018/04/29.
 */

package com.community.service.impl;

import com.community.common.service.AbstractCurdService;
import com.community.dao.mapper.OrganizationMapper;
import com.community.domain.model.db.OrganizationDO;
import com.community.domain.request.OrganizationRequest;
import com.community.domain.response.OrganizationResponse;
import com.community.service.OrganizationService;
import org.springframework.stereotype.Service;


@Service
public class OrganizationServiceImpl extends AbstractCurdService<Long, OrganizationRequest, OrganizationDO, OrganizationResponse, OrganizationMapper>
        implements OrganizationService {

}
