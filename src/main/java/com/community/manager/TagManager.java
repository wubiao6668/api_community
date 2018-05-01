/**
 * Created by wubiao on - 2018/04/29.
 */

package com.community.manager;

import com.community.dao.mapper.TagMapper;
import com.community.domain.core.Page;
import com.community.domain.model.db.TagDO;
import com.community.domain.request.TagRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Service
public class TagManager {

    @Autowired
    private TagMapper tagMapper;

    public Future<Page<TagDO>> listPageAsync(TagRequest request) {
        return CompletableFuture.supplyAsync(() -> tagMapper.listPage(request));
    }
}
