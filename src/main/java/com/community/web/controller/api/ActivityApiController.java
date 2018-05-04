/**
 * Created by wubiao on - 2018/05/01.
 */

package com.community.web.controller.api;

import com.community.common.annotations.RequestJsonParam;
import com.community.domain.core.Response;
import com.community.domain.request.ActivityRequest;
import com.community.domain.response.ActivityResponse;
import com.community.manager.ActivityManager;
import com.community.service.ActivityService;
import com.community.web.core.AbstractAction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api.activity")
public class ActivityApiController extends AbstractAction<ActivityService, ActivityManager> {

    /**
     * 活动列表
     *
     * @param request
     * @return
     */
    @RequestMapping("list")
    public Response<ActivityResponse> listPage(@RequestJsonParam("request") ActivityRequest request) {
        return service.listPage(request, ActivityResponse.class);
    }

    /**
     * 活动详情
     *
     * @param request
     * @return
     */
    @RequestMapping("detail")
    public Response<ActivityResponse> activityDetail(@RequestJsonParam("request") ActivityRequest request) {
        return manager.activityDetail(request);
    }


}
