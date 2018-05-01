/**
 * Created by wubiao on - 2018/04/29.
 */

package com.community.manager;

import com.community.dao.mapper.ReplyMapper;
import com.community.domain.core.Page;
import com.community.domain.model.db.ReplyDO;
import com.community.domain.request.ReplyRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Service
public class ReplyManager {

    @Autowired
    private ReplyMapper replyMapper;

    public Future<Page<ReplyDO>> listPageAsync(ReplyRequest request) {
        return CompletableFuture.supplyAsync(() -> replyMapper.listPage(request));
    }
}
