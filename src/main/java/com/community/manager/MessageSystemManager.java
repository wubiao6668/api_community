/**
 * Created by wubiao on - 2018/04/29.
 */

package com.community.manager;

import com.community.common.util.BeanUtils;
import com.community.dao.mapper.MessageSystemMapper;
import com.community.domain.core.Page;
import com.community.domain.core.Pagination;
import com.community.domain.core.Response;
import com.community.domain.model.db.MessageSystemDO;
import com.community.domain.request.MessageSystemRequest;
import com.community.domain.response.MessageSystemResponse;
import com.community.domain.response.vo.MessageSystemVO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Service
public class MessageSystemManager {

    @Autowired
    private MessageSystemMapper messageSystemMapper;

    public Response<MessageSystemResponse> listPage(MessageSystemRequest request) {
        if (null == request) {
            request = new MessageSystemRequest();
        }
        Page<MessageSystemDO> messageSystemDOPage = messageSystemMapper.listPage(request);
        List<MessageSystemDO> contentDOList = Optional.ofNullable(messageSystemDOPage).flatMap(Page::getData).orElse(null);
        Pagination pagination = Optional.ofNullable(messageSystemDOPage).flatMap(Page::getPagination).orElse(null);
        Boolean hasMore = Optional.ofNullable(messageSystemDOPage).map(Page::getHasMore).orElse(null);
        List<MessageSystemVO> messageSystemVOList = null;
        if (CollectionUtils.isNotEmpty(contentDOList)) {
            messageSystemVOList = BeanUtils.list2list(contentDOList, MessageSystemVO.class);
        }
        MessageSystemResponse messageSystemResponse = new MessageSystemResponse();
        messageSystemResponse.setMsgList(messageSystemVOList);
        messageSystemResponse.setPagination(pagination);
        messageSystemResponse.setHasMore(hasMore);
        return Response.success(messageSystemResponse);
    }


    public Future<Response<MessageSystemResponse>> listPageAsync(MessageSystemRequest request) {
        return CompletableFuture.supplyAsync(() -> this.listPage(request));
    }
}
