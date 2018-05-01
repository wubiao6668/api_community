/**
 * Created by wubiao on - 2018/04/29.
 */

package com.community.manager;

import com.community.dao.mapper.UserInfoMapper;
import com.community.domain.core.Page;
import com.community.domain.model.db.UserInfoDO;
import com.community.domain.request.UserInfoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Service
public class UserInfoManager {

    @Autowired
    private UserInfoMapper userInfoMapper;

    public Future<Page<UserInfoDO>> listPageAsync(UserInfoRequest request) {
        return CompletableFuture.supplyAsync(() -> userInfoMapper.listPage(request));
    }
}
