/**
 * Created by wubiao on - 2018/05/25.
 */

package com.community.manager;

import com.community.dao.mapper.GroupMessageMapper;
import com.community.domain.core.Page;
import com.community.domain.model.db.GroupMessageDO;
import com.community.domain.request.GroupMessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Service
public class GroupMessageManager {

    @Autowired
    private GroupMessageMapper groupMessageMapper;

    public Future<Page<GroupMessageDO>> listPageAsync(GroupMessageRequest request) {
        return CompletableFuture.supplyAsync(() -> groupMessageMapper.listPage(request));
    }
}
