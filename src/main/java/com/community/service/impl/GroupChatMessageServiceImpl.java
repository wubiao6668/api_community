/**
 * Created by wubiao on - 2018/05/30.
 */

package com.community.service.impl;

import com.community.common.service.AbstractCurdService;
import com.community.dao.mapper.GroupChatMessageMapper;
import com.community.domain.model.db.GroupChatMessageDO;
import com.community.domain.request.GroupChatMessageRequest;
import com.community.domain.response.GroupChatMessageResponse;
import com.community.service.GroupChatMessageService;
import org.springframework.stereotype.Service;


@Service
public class GroupChatMessageServiceImpl extends AbstractCurdService<Long, GroupChatMessageRequest, GroupChatMessageDO, GroupChatMessageResponse, GroupChatMessageMapper>
        implements GroupChatMessageService {

}
