/**
 * Created by wubiao on - 2018/05/30.
 */

package com.community.manager;

import com.community.dao.mapper.UserChatInfoMapper;
import com.community.domain.core.Page;
import com.community.domain.model.db.UserChatInfoDO;
import com.community.domain.request.UserChatInfoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Service
public class UserChatInfoManager {

    @Autowired
    private UserChatInfoMapper userChatInfoMapper;

    public Future<Page<UserChatInfoDO>> listPageAsync(UserChatInfoRequest request) {
        return CompletableFuture.supplyAsync(() -> userChatInfoMapper.listPage(request));
    }
}
