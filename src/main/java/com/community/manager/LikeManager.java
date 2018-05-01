/**
 * Created by wubiao on - 2018/04/29.
 */

package com.community.manager;

import com.community.dao.mapper.LikeMapper;
import com.community.domain.core.Page;
import com.community.domain.model.db.LikeDO;
import com.community.domain.request.LikeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Service
public class LikeManager {

    @Autowired
    private LikeMapper likeMapper;

    public Future<Page<LikeDO>> listPageAsync(LikeRequest request) {
        return CompletableFuture.supplyAsync(() -> likeMapper.listPage(request));
    }
}
