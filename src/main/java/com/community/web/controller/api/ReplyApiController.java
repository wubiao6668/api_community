/**
 * Created by wubiao on - 2018/05/01.
 */

package com.community.web.controller.api;

import com.community.domain.core.Response;
import com.community.domain.request.ReplyRequest;
import com.community.domain.response.ReplyResponse;
import com.community.manager.ReplyManager;
import com.community.service.ReplyService;
import com.community.web.core.AbstractAction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api.reply")
public class ReplyApiController extends AbstractAction<ReplyService, ReplyManager> {

    @RequestMapping("list")
    public Response<ReplyResponse> listPage(ReplyRequest replyRequest) {
        return manager.listPage(replyRequest);
    }


}
