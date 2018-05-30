/**
 * Created by wubiao on - 2018/05/30.
 */

package com.community.manager;

import com.community.dao.mapper.GroupChatMessageMapper;
import com.community.domain.core.Page;
import com.community.domain.model.db.GroupChatMessageDO;
import com.community.domain.request.GroupChatMessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Service
public class GroupChatMessageManager {

    @Autowired
    private GroupChatMessageMapper groupChatMessageMapper;

    public Future<Page<GroupChatMessageDO>> listPageAsync(GroupChatMessageRequest request) {
        return CompletableFuture.supplyAsync(() -> groupChatMessageMapper.listPage(request));
    }
}
