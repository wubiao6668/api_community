/**
 * Created by wubiao on - 2018/05/01.
 */

package com.community.web.controller.api;

import com.community.common.annotations.RequestJsonParam;
import com.community.domain.core.Response;
import com.community.domain.request.ContentRequest;
import com.community.manager.ContentManager;
import com.community.service.ContentService;
import com.community.web.core.AbstractAction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api.content")
public class ContentApiController extends AbstractAction<ContentService, ContentManager> {

    @RequestMapping("list.page")
    public Response listContentPage(@RequestJsonParam("request") ContentRequest request) {
        return manager.listContentPage(request);
    }

}
