/**
 * Created by wubiao on - 2018/04/30.
 */

package com.community.service.impl;

import com.community.common.service.AbstractCurdService;
import com.community.dao.mapper.OrganizationMemberMapper;
import com.community.domain.model.db.OrganizationMemberDO;
import com.community.domain.request.OrganizationMemberRequest;
import com.community.domain.response.OrganizationMemberResponse;
import com.community.service.OrganizationMemberService;
import org.springframework.stereotype.Service;


@Service
public class OrganizationMemberServiceImpl extends AbstractCurdService<Long, OrganizationMemberRequest, OrganizationMemberDO, OrganizationMemberResponse, OrganizationMemberMapper>
        implements OrganizationMemberService {

}
