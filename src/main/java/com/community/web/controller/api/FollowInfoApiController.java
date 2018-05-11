/**
 * Created by wubiao on - 2018/05/01.
 */

package com.community.web.controller.api;

import com.community.common.annotations.RequestJsonParam;
import com.community.domain.core.Response;
import com.community.domain.request.FollowInfoRequest;
import com.community.domain.response.FollowInfoResponse;
import com.community.domain.session.LoginContext;
import com.community.manager.FollowInfoManager;
import com.community.service.FollowInfoService;
import com.community.web.core.AbstractAction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api.follow")
public class FollowInfoApiController extends AbstractAction<FollowInfoService, FollowInfoManager> {

    @RequestMapping("mine.question")
    public Response<FollowInfoResponse> mineQuestion(@RequestJsonParam("request") FollowInfoRequest request) {
        if (null == request) {
            request = new FollowInfoRequest();
        }
        request.setUserId(LoginContext.getUserId());
        return manager.listPage(request);
    }

}
