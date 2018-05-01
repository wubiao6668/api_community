/**
 * Created by wubiao on - 2018/04/29.
 */

package com.community.manager;

import com.community.dao.mapper.MessageSystemDetailMapper;
import com.community.domain.core.Page;
import com.community.domain.model.db.MessageSystemDetailDO;
import com.community.domain.request.MessageSystemDetailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Service
public class MessageSystemDetailManager {

    @Autowired
    private MessageSystemDetailMapper messageSystemDetailMapper;

    public Future<Page<MessageSystemDetailDO>> listPageAsync(MessageSystemDetailRequest request) {
        return CompletableFuture.supplyAsync(() -> messageSystemDetailMapper.listPage(request));
    }
}
