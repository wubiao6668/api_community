/**
 * Created by wubiao on - 2018/05/30.
 */

package com.community.service.impl;

import com.community.common.service.AbstractCurdService;
import com.community.dao.mapper.UserChatMessageMapper;
import com.community.domain.model.db.UserChatMessageDO;
import com.community.domain.request.UserChatMessageRequest;
import com.community.domain.response.UserChatMessageResponse;
import com.community.service.UserChatMessageService;
import org.springframework.stereotype.Service;


@Service
public class UserChatMessageServiceImpl extends AbstractCurdService<Long, UserChatMessageRequest, UserChatMessageDO, UserChatMessageResponse, UserChatMessageMapper>
        implements UserChatMessageService {

}
