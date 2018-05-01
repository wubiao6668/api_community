/**
 * Created by wubiao on - 2018/04/29.
 */

package com.community.manager;

import com.community.dao.mapper.ContentMapper;
import com.community.domain.core.Page;
import com.community.domain.model.db.ContentDO;
import com.community.domain.request.ContentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Service
public class ContentManager {

    @Autowired
    private ContentMapper contentMapper;

    public Future<Page<ContentDO>> listPageAsync(ContentRequest request) {
        return CompletableFuture.supplyAsync(() -> contentMapper.listPage(request));
    }
}
