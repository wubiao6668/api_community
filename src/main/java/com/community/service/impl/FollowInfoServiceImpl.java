/**
 * Created by wubiao on - 2018/04/29.
 */

package com.community.service.impl;

import com.community.common.service.AbstractCurdService;
import com.community.dao.mapper.FollowInfoMapper;
import com.community.domain.model.db.FollowInfoDO;
import com.community.domain.request.FollowInfoRequest;
import com.community.domain.response.FollowInfoResponse;
import com.community.service.FollowInfoService;
import org.springframework.stereotype.Service;


@Service
public class FollowInfoServiceImpl extends AbstractCurdService<Long, FollowInfoRequest, FollowInfoDO, FollowInfoResponse, FollowInfoMapper>
        implements FollowInfoService {

}
