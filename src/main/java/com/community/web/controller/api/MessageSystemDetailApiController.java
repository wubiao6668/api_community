/**
 * Created by wubiao on - 2018/05/01.
 */

package com.community.web.controller.api;

import com.community.domain.core.Response;
import com.community.domain.request.MessageSystemDetailRequest;
import com.community.domain.response.MessageSystemDetailResponse;
import com.community.domain.session.LoginContext;
import com.community.manager.MessageSystemDetailManager;
import com.community.service.MessageSystemDetailService;
import com.community.web.core.AbstractAction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api.msgDetail")
public class MessageSystemDetailApiController extends AbstractAction<MessageSystemDetailService, MessageSystemDetailManager> {

    @RequestMapping("mine")
    public Response<MessageSystemDetailResponse> mineMsgDetail(MessageSystemDetailRequest request) {
        if (null == request) {
            request = new MessageSystemDetailRequest();
        }
        request.setReceiverUserId(LoginContext.getUserId());
        return manager.listPage(request);
    }

}
