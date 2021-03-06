/**
 * Created by wubiao on - 2018/05/01.
 */

package com.community.web.controller.api;

import com.community.common.annotations.RequestJsonParam;
import com.community.domain.core.Response;
import com.community.domain.request.OrganizationRequest;
import com.community.domain.response.OrganizationResponse;
import com.community.manager.OrganizationManager;
import com.community.service.OrganizationService;
import com.community.web.core.AbstractAction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api.org")
public class OrganizationApiController extends AbstractAction<OrganizationService, OrganizationManager> {


    @RequestMapping("detail")
    public Response<OrganizationResponse> orgDetail(@RequestJsonParam("request") OrganizationRequest request) {
        return manager.organizationDetail(request);
    }

    @RequestMapping("introduce")
    public Response<OrganizationResponse> orgIntroduce(@RequestJsonParam("request") OrganizationRequest request) {
        return manager.organizationIntroduce(request);
    }

}
