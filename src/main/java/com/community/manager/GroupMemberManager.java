/**
 * Created by wubiao on - 2018/05/25.
 */

package com.community.manager;

import com.community.dao.mapper.GroupMemberMapper;
import com.community.domain.core.Page;
import com.community.domain.model.db.GroupMemberDO;
import com.community.domain.request.GroupMemberRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Service
public class GroupMemberManager {

    @Autowired
    private GroupMemberMapper groupMemberMapper;

    public Future<Page<GroupMemberDO>> listPageAsync(GroupMemberRequest request) {
        return CompletableFuture.supplyAsync(() -> groupMemberMapper.listPage(request));
    }
}
