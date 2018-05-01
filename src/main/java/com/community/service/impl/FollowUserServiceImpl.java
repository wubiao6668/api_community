/**
 * Created by wubiao on - 2018/04/29.
 */

package com.community.service.impl;

import com.community.common.service.AbstractCurdService;
import com.community.dao.mapper.FollowUserMapper;
import com.community.domain.model.db.FollowUserDO;
import com.community.domain.request.FollowUserRequest;
import com.community.domain.response.FollowUserResponse;
import com.community.service.FollowUserService;
import org.springframework.stereotype.Service;


@Service
public class FollowUserServiceImpl extends AbstractCurdService<Long, FollowUserRequest, FollowUserDO, FollowUserResponse, FollowUserMapper>
        implements FollowUserService {

}
