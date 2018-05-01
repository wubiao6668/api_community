/**
 * Created by wubiao on - 2018/04/30.
 */

package com.community.service.impl;

import com.community.common.service.AbstractCurdService;
import com.community.dao.mapper.ActivityMemberMapper;
import com.community.domain.model.db.ActivityMemberDO;
import com.community.domain.request.ActivityMemberRequest;
import com.community.domain.response.ActivityMemberResponse;
import com.community.service.ActivityMemberService;
import org.springframework.stereotype.Service;


@Service
public class ActivityMemberServiceImpl extends AbstractCurdService<Long, ActivityMemberRequest, ActivityMemberDO, ActivityMemberResponse, ActivityMemberMapper>
        implements ActivityMemberService {

}
