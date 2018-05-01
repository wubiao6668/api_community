package com.community.web.controller;

import com.community.domain.core.Response;
import com.community.domain.request.TagRequest;
import com.community.domain.response.TagResponse;
import com.community.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class IndexApiAction {

    @Autowired
    private TagService tagService;

    @RequestMapping("check")
    public Response check() {
        TagRequest tagRequest = new TagRequest();
        return tagService.listPage(tagRequest, TagResponse.class);
    }

}
