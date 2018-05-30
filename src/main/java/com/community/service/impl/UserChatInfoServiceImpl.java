/**
 * Created by wubiao on - 2018/05/30.
 */

package com.community.service.impl;

import com.community.common.service.AbstractCurdService;
import com.community.dao.mapper.UserChatInfoMapper;
import com.community.domain.model.db.UserChatInfoDO;
import com.community.domain.request.UserChatInfoRequest;
import com.community.domain.response.UserChatInfoResponse;
import com.community.service.UserChatInfoService;
import org.springframework.stereotype.Service;


@Service
public class UserChatInfoServiceImpl extends AbstractCurdService<Long, UserChatInfoRequest, UserChatInfoDO, UserChatInfoResponse, UserChatInfoMapper>
        implements UserChatInfoService {

}
