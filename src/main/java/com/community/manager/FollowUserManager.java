/**
 * Created by wubiao on - 2018/04/29.
 */

package com.community.manager;

import com.community.dao.mapper.FollowUserMapper;
import com.community.domain.core.Page;
import com.community.domain.model.db.FollowUserDO;
import com.community.domain.request.FollowUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Service
public class FollowUserManager {

    @Autowired
    private FollowUserMapper followUserMapper;

    public Future<Page<FollowUserDO>> listPageAsync(FollowUserRequest request) {
        return CompletableFuture.supplyAsync(() -> followUserMapper.listPage(request));
    }
}
