/**
 * Created by wubiao on - 2018/05/30.
 */

package com.community.service.impl;

import com.community.common.service.AbstractCurdService;
import com.community.dao.mapper.GroupChatInfoMapper;
import com.community.domain.model.db.GroupChatInfoDO;
import com.community.domain.request.GroupChatInfoRequest;
import com.community.domain.response.GroupChatInfoResponse;
import com.community.service.GroupChatInfoService;
import org.springframework.stereotype.Service;


@Service
public class GroupChatInfoServiceImpl extends AbstractCurdService<Long, GroupChatInfoRequest, GroupChatInfoDO, GroupChatInfoResponse, GroupChatInfoMapper>
        implements GroupChatInfoService {

}
