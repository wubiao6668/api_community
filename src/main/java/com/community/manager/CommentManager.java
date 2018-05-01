/**
 * Created by wubiao on - 2018/04/29.
 */

package com.community.manager;

import com.community.dao.mapper.CommentMapper;
import com.community.domain.core.Page;
import com.community.domain.model.db.CommentDO;
import com.community.domain.request.CommentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Service
public class CommentManager {

    @Autowired
    private CommentMapper commentMapper;

    public Future<Page<CommentDO>> listPageAsync(CommentRequest request) {
        return CompletableFuture.supplyAsync(() -> commentMapper.listPage(request));
    }
}
