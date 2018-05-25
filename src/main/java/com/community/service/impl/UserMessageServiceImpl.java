/**
 * Created by wubiao on - 2018/05/25.
 */

package com.community.service.impl;

import com.community.common.service.AbstractCurdService;
import com.community.dao.mapper.UserMessageMapper;
import com.community.domain.model.db.UserMessageDO;
import com.community.domain.request.UserMessageRequest;
import com.community.domain.response.UserMessageResponse;
import com.community.service.UserMessageService;
import org.springframework.stereotype.Service;


@Service
public class UserMessageServiceImpl extends AbstractCurdService<Long, UserMessageRequest, UserMessageDO, UserMessageResponse, UserMessageMapper>
        implements UserMessageService {

}
