/**
 * Created by wubiao on - 2018/05/25.
 */

package com.community.service.impl;

import com.community.common.service.AbstractCurdService;
import com.community.dao.mapper.UserMessageDetailMapper;
import com.community.domain.model.db.UserMessageDetailDO;
import com.community.domain.request.UserMessageDetailRequest;
import com.community.domain.response.UserMessageDetailResponse;
import com.community.service.UserMessageDetailService;
import org.springframework.stereotype.Service;


@Service
public class UserMessageDetailServiceImpl extends AbstractCurdService<Long, UserMessageDetailRequest, UserMessageDetailDO, UserMessageDetailResponse, UserMessageDetailMapper>
        implements UserMessageDetailService {

}
