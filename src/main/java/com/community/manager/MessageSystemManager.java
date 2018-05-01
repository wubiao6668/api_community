/**
 * Created by wubiao on - 2018/04/29.
 */

package com.community.manager;

import com.community.dao.mapper.MessageSystemMapper;
import com.community.domain.core.Page;
import com.community.domain.model.db.MessageSystemDO;
import com.community.domain.request.MessageSystemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Service
public class MessageSystemManager {

    @Autowired
    private MessageSystemMapper messageSystemMapper;

    public Future<Page<MessageSystemDO>> listPageAsync(MessageSystemRequest request) {
        return CompletableFuture.supplyAsync(() -> messageSystemMapper.listPage(request));
    }
}
