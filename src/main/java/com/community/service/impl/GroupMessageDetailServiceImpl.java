/**
 * Created by wubiao on - 2018/05/25.
 */

package com.community.service.impl;

import com.community.common.service.AbstractCurdService;
import com.community.dao.mapper.GroupMessageDetailMapper;
import com.community.domain.model.db.GroupMessageDetailDO;
import com.community.domain.request.GroupMessageDetailRequest;
import com.community.domain.response.GroupMessageDetailResponse;
import com.community.service.GroupMessageDetailService;
import org.springframework.stereotype.Service;


@Service
public class GroupMessageDetailServiceImpl extends AbstractCurdService<Long, GroupMessageDetailRequest, GroupMessageDetailDO, GroupMessageDetailResponse, GroupMessageDetailMapper>
        implements GroupMessageDetailService {

}
