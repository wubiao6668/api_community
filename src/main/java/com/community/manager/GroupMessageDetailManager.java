/**
 * Created by wubiao on - 2018/05/25.
 */

package com.community.manager;

import com.community.dao.mapper.GroupMessageDetailMapper;
import com.community.domain.core.Page;
import com.community.domain.model.db.GroupMessageDetailDO;
import com.community.domain.request.GroupMessageDetailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Service
public class GroupMessageDetailManager {

    @Autowired
    private GroupMessageDetailMapper groupMessageDetailMapper;

    public Future<Page<GroupMessageDetailDO>> listPageAsync(GroupMessageDetailRequest request) {
        return CompletableFuture.supplyAsync(() -> groupMessageDetailMapper.listPage(request));
    }
}
