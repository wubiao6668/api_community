/**
 * Created by wubiao on - 2018/05/25.
 */

package com.community.manager;

import com.community.dao.mapper.UserMessageMapper;
import com.community.domain.core.Page;
import com.community.domain.model.db.UserMessageDO;
import com.community.domain.request.UserMessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Service
public class UserMessageManager {

    @Autowired
    private UserMessageMapper userMessageMapper;

    public Future<Page<UserMessageDO>> listPageAsync(UserMessageRequest request) {
        return CompletableFuture.supplyAsync(() -> userMessageMapper.listPage(request));
    }
}
