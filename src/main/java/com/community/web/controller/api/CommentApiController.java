/**
 * Created by wubiao on - 2018/05/01.
 */

package com.community.web.controller.api;

import com.community.domain.core.Page;
import com.community.domain.core.Response;
import com.community.domain.request.CommentRequest;
import com.community.domain.response.CommentResponse;
import com.community.manager.CommentManager;
import com.community.service.CommentService;
import com.community.web.core.AbstractAction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api.comment")
public class CommentApiController extends AbstractAction<CommentService, CommentManager> {

    @RequestMapping("list")
    public Response<Page<CommentResponse>> listPage(CommentRequest commentRequest) {
        return manager.listPageByBizId(commentRequest);
    }

}
