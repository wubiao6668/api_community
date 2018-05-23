/**
 * Created by wubiao on - 2018/05/01.
 */

package com.community.web.controller.api;

import com.community.domain.core.Response;
import com.community.domain.request.MessageSystemRequest;
import com.community.domain.response.MessageSystemResponse;
import com.community.domain.session.LoginContext;
import com.community.manager.MessageSystemManager;
import com.community.service.MessageSystemService;
import com.community.web.core.AbstractAction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.community.common.constant.Constant.ShowEnum;

@Controller
@RequestMapping("api.msg")
public class MessageSystemApiController extends AbstractAction<MessageSystemService, MessageSystemManager> {

    @RequestMapping("mine")
    public Response<MessageSystemResponse> mineMsgSystem(MessageSystemRequest request) {
        if (null == request) {
            request = new MessageSystemRequest();
        }
        request.setUserId(LoginContext.getUserId());
        request.setIsShow(ShowEnum.SHOW.getCode());
        return manager.listPage(request);
    }
}
