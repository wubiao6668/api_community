/**
 * Created by wubiao on - 2018/05/01.
 */

package com.community.web.controller.api;

import com.community.manager.FollowUserManager;
import com.community.service.FollowUserService;
import com.community.web.core.AbstractAction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("api.follow")
public class FollowUserApiController extends AbstractAction<FollowUserService, FollowUserManager> {



}
