/**
 * Created by wubiao on - 2018/05/30.
 */

package com.community.manager;

import com.community.dao.mapper.GroupChatInfoMapper;
import com.community.domain.core.Page;
import com.community.domain.model.db.GroupChatInfoDO;
import com.community.domain.request.GroupChatInfoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Service
public class GroupChatInfoManager {

    @Autowired
    private GroupChatInfoMapper groupChatInfoMapper;

    public Future<Page<GroupChatInfoDO>> listPageAsync(GroupChatInfoRequest request) {
        return CompletableFuture.supplyAsync(() -> groupChatInfoMapper.listPage(request));
    }
}
