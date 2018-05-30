/**
 * Created by wubiao on - 2018/05/30.
 */

package com.community.manager;

import com.community.dao.mapper.UserChatMessageMapper;
import com.community.domain.core.Page;
import com.community.domain.model.db.UserChatMessageDO;
import com.community.domain.request.UserChatMessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Service
public class UserChatMessageManager {

    @Autowired
    private UserChatMessageMapper userChatMessageMapper;

    public Future<Page<UserChatMessageDO>> listPageAsync(UserChatMessageRequest request) {
        return CompletableFuture.supplyAsync(() -> userChatMessageMapper.listPage(request));
    }
}
