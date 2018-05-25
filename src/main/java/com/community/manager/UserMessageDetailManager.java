/**
 * Created by wubiao on - 2018/05/25.
 */

package com.community.manager;

import com.community.dao.mapper.UserMessageDetailMapper;
import com.community.domain.core.Page;
import com.community.domain.model.db.UserMessageDetailDO;
import com.community.domain.request.UserMessageDetailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Service
public class UserMessageDetailManager {

    @Autowired
    private UserMessageDetailMapper userMessageDetailMapper;

    public Future<Page<UserMessageDetailDO>> listPageAsync(UserMessageDetailRequest request) {
        return CompletableFuture.supplyAsync(() -> userMessageDetailMapper.listPage(request));
    }
}
