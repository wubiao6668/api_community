/**
 * Created by wubiao on - 2018/04/29.
 */

package com.community.manager;

import com.community.dao.mapper.FollowInfoMapper;
import com.community.domain.core.Page;
import com.community.domain.model.db.FollowInfoDO;
import com.community.domain.request.FollowInfoRequest;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Service
public class FollowInfoManager {

    @Autowired
    private FollowInfoMapper followInfoMapper;

    public Future<Page<FollowInfoDO>> listPageAsync(FollowInfoRequest request) {
        return CompletableFuture.supplyAsync(() -> followInfoMapper.listPage(request));
    }

    public Map<Long, FollowInfoDO> getByUserIdBizIdAndType(FollowInfoRequest request) {
        if (null == request.getBizId() || null == request.getType()
                || (null == request.getUserId() && CollectionUtils.isEmpty(request.getUserIdSet()))) {
            return null;
        }
        return followInfoMapper.getByUserIdBizIdAndType(request);
    }

    public Future<Map<Long, FollowInfoDO>> getByUserIdBizIdAndTypeAsync(FollowInfoRequest request) {
        return CompletableFuture.supplyAsync(() -> this.getByUserIdBizIdAndType(request));
    }
}
