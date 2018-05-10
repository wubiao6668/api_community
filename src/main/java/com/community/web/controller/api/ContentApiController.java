/**
 * Created by wubiao on - 2018/05/01.
 */

package com.community.web.controller.api;

import com.community.common.annotations.RequestJsonParam;
import com.community.domain.core.Response;
import com.community.domain.request.ContentRequest;
import com.community.domain.response.ContentResponse;
import com.community.domain.session.LoginContext;
import com.community.manager.ContentManager;
import com.community.service.ContentService;
import com.community.web.core.AbstractAction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api.content")
public class ContentApiController extends AbstractAction<ContentService, ContentManager> {

    @RequestMapping("list.page")
    public Response<ContentResponse> listContentPage(@RequestJsonParam("request") ContentRequest request) {
        return manager.listContentPage(request);
    }

    @RequestMapping("detail")
    public Response<ContentResponse> contentDetail(@RequestJsonParam("request") ContentRequest request) {
        return manager.contentDetail(request);
    }

    @RequestMapping("mine.question")
    public Response<ContentResponse> mineQuestion(@RequestJsonParam("request") ContentRequest request) {
        if (null == request) {
            return Response.success();
        }
        request.setUserId(LoginContext.getUserId());
        return manager.listContentPage(request);
    }

    @RequestMapping("mine.answer")
    public Response<ContentResponse> mineAnswer(@RequestJsonParam("request") ContentRequest request) {
        if (null == request) {
            return Response.success();
        }
        request.setUserId(LoginContext.getUserId());
        return manager.listContentPage(request);
    }


}
