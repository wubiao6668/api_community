/**
 * Created by wubiao on - 2018/05/25.
 */

package com.community.manager;

import com.community.dao.mapper.GroupInfoMapper;
import com.community.domain.core.Page;
import com.community.domain.model.db.GroupInfoDO;
import com.community.domain.request.GroupInfoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Service
public class GroupInfoManager {

    @Autowired
    private GroupInfoMapper groupInfoMapper;

    public Future<Page<GroupInfoDO>> listPageAsync(GroupInfoRequest request) {
        return CompletableFuture.supplyAsync(() -> groupInfoMapper.listPage(request));
    }
}
