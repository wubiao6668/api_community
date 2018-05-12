/**
 * Created by wubiao on - 2018/05/01.
 */

package com.community.web.controller.api;

import com.community.domain.core.Response;
import com.community.domain.request.FavoriteRequest;
import com.community.domain.response.FavoriteResponse;
import com.community.manager.FavoriteManager;
import com.community.service.FavoriteService;
import com.community.web.core.AbstractAction;
import com.google.common.collect.Sets;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.community.common.constant.Constant.TypeEnum;

@Controller
@RequestMapping("api.favorite")
public class FavoriteApiController extends AbstractAction<FavoriteService, FavoriteManager> {

    @RequestMapping("mine.post")
    public Response<FavoriteResponse> minePost(FavoriteRequest request) {
        if (null == request) {
            request = new FavoriteRequest();
            request.setTypeSet(Sets.newHashSet(TypeEnum.ACTIVITY.getCode(), TypeEnum.ORG.getCode()));
        }
        return manager.listPage(request);
    }

    @RequestMapping("mine.answer")
    public Response<FavoriteResponse> mineAnswer(FavoriteRequest request) {
        if (null == request) {
            request = new FavoriteRequest();
            request.setType(TypeEnum.ANSWER.getCode());
        }
        return manager.listPage(request);
    }


}
