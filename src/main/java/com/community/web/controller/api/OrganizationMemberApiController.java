/**
 * Created by wubiao on - 2018/05/01.
 */

package com.community.web.controller.api;

import com.community.domain.core.Response;
import com.community.domain.request.OrganizationMemberRequest;
import com.community.domain.response.OrganizationMemberResponse;
import com.community.manager.OrganizationMemberManager;
import com.community.service.OrganizationMemberService;
import com.community.web.core.AbstractAction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api.orgMember")
public class OrganizationMemberApiController extends AbstractAction<OrganizationMemberService, OrganizationMemberManager> {

    @RequestMapping("list")
    public Response<OrganizationMemberResponse> orgMemberList(OrganizationMemberRequest request) {
        OrganizationMemberResponse organizationMemberResponse = new OrganizationMemberResponse();
        organizationMemberResponse.setOrgMemberListPage(manager.listPage(request));
        return Response.success(organizationMemberResponse);
    }

}
