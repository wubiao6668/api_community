/**
 * Created by wubiao on - 2018/05/25.
 */

package com.community.service.impl;

import com.community.common.service.AbstractCurdService;
import com.community.dao.mapper.GroupMessageMapper;
import com.community.domain.model.db.GroupMessageDO;
import com.community.domain.request.GroupMessageRequest;
import com.community.domain.response.GroupMessageResponse;
import com.community.service.GroupMessageService;
import org.springframework.stereotype.Service;


@Service
public class GroupMessageServiceImpl extends AbstractCurdService<Long, GroupMessageRequest, GroupMessageDO, GroupMessageResponse, GroupMessageMapper>
        implements GroupMessageService {

}
