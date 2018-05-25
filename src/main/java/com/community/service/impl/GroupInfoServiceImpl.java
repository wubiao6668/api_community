/**
 * Created by wubiao on - 2018/05/25.
 */

package com.community.service.impl;

import com.community.common.service.AbstractCurdService;
import com.community.dao.mapper.GroupInfoMapper;
import com.community.domain.model.db.GroupInfoDO;
import com.community.domain.request.GroupInfoRequest;
import com.community.domain.response.GroupInfoResponse;
import com.community.service.GroupInfoService;
import org.springframework.stereotype.Service;


@Service
public class GroupInfoServiceImpl extends AbstractCurdService<Long, GroupInfoRequest, GroupInfoDO, GroupInfoResponse, GroupInfoMapper>
        implements GroupInfoService {

}
