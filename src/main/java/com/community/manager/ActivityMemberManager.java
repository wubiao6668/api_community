/**
 * Created by wubiao on - 2018/04/30.
 */

package com.community.manager;

import com.community.dao.mapper.ActivityMemberMapper;
import com.community.domain.core.Page;
import com.community.domain.model.db.ActivityMemberDO;
import com.community.domain.request.ActivityMemberRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Service
public class ActivityMemberManager {

    @Autowired
    private ActivityMemberMapper activityMemberMapper;

    public Future<Page<ActivityMemberDO>> listPageAsync(ActivityMemberRequest request) {
        return CompletableFuture.supplyAsync(() -> activityMemberMapper.listPage(request));
    }
}
