/**
 * Created by wubiao on - 2018/04/29.
 */

package com.community.manager;

import com.community.dao.mapper.ActivityMapper;
import com.community.domain.core.Page;
import com.community.domain.model.db.ActivityDO;
import com.community.domain.request.ActivityRequest;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Service
public class ActivityManager {

    @Autowired
    private ActivityMapper activityMapper;

    public Future<Page<ActivityDO>> listPageAsync(ActivityRequest request) {
        return CompletableFuture.supplyAsync(() -> activityMapper.listPage(request));
    }

    Future<Map<Long, ActivityDO>> getActivityByIdsAsync(Set<Long> activityIdSets) {
        if (CollectionUtils.isEmpty(activityIdSets)) {
            return null;
        }
        return CompletableFuture.supplyAsync(() -> activityMapper.getByKeys(activityIdSets));
    }
}
