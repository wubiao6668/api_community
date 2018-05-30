package com.community.web.controller;

import com.community.domain.bo.websocket.WsChannelFriendBO;
import com.community.domain.core.Response;
import com.community.domain.request.TagRequest;
import com.community.domain.response.TagResponse;
import com.community.manager.WsChannelManager;
import com.community.service.TagService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class IndexApiAction {

    @Autowired
    private TagService tagService;

    @Autowired
    private WsChannelManager wsChannelManager;

    @RequestMapping("check")
    public Response check() {
        TagRequest tagRequest = new TagRequest();
        return tagService.listPage(tagRequest, TagResponse.class);
    }

    @RequestMapping("check2")
    public void check2() {
        WsChannelFriendBO wsChannelBO = null;
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            wsChannelBO = new WsChannelFriendBO();
            wsChannelBO.setFromUserId(12121L);
            wsChannelBO.setToUserId(12121L);
            wsChannelBO.setMessage(RandomStringUtils.randomAlphanumeric(200));
            wsChannelManager.sendToFriend(wsChannelBO);
        }
        System.out.println(System.currentTimeMillis() - start);

    }


}
