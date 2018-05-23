/**
 * Created by wubiao on - 2018/04/29.
 */

package com.community.manager;

import com.community.common.util.BeanUtils;
import com.community.dao.mapper.MessageSystemDetailMapper;
import com.community.domain.core.Page;
import com.community.domain.core.Pagination;
import com.community.domain.core.Response;
import com.community.domain.model.db.MessageSystemDetailDO;
import com.community.domain.request.MessageSystemDetailRequest;
import com.community.domain.response.MessageSystemDetailResponse;
import com.community.domain.response.vo.MessageSystemDetailVO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Service
public class MessageSystemDetailManager {

    @Autowired
    private MessageSystemDetailMapper messageSystemDetailMapper;

    public Response<MessageSystemDetailResponse> listPage(MessageSystemDetailRequest request) {
        if (null == request) {
            request = new MessageSystemDetailRequest();
        }
        Page<MessageSystemDetailDO> messageSystemDetailDOPage = messageSystemDetailMapper.listPage(request);
        List<MessageSystemDetailDO> messageSystemDetailDOList = Optional.ofNullable(messageSystemDetailDOPage).flatMap(Page::getData).orElse(null);
        Pagination pagination = Optional.ofNullable(messageSystemDetailDOPage).flatMap(Page::getPagination).orElse(null);
        Boolean hasMore = Optional.ofNullable(messageSystemDetailDOPage).map(Page::getHasMore).orElse(null);
        List<MessageSystemDetailVO> messageSystemDetailVOList = null;
        if (CollectionUtils.isNotEmpty(messageSystemDetailDOList)) {
            messageSystemDetailVOList = BeanUtils.list2list(messageSystemDetailDOList, MessageSystemDetailVO.class);
        }
        MessageSystemDetailResponse messageSystemDetailResponse = new MessageSystemDetailResponse();
        messageSystemDetailResponse.setMsgDetailList(messageSystemDetailVOList);
        messageSystemDetailResponse.setPagination(pagination);
        messageSystemDetailResponse.setHasMore(hasMore);
        return Response.success(messageSystemDetailResponse);
    }

    public Future<Page<MessageSystemDetailDO>> listPageAsync(MessageSystemDetailRequest request) {
        return CompletableFuture.supplyAsync(() -> messageSystemDetailMapper.listPage(request));
    }
}
