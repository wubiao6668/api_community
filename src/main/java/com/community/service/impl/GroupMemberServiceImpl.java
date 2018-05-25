/**
 * Created by wubiao on - 2018/05/25.
 */

package com.community.service.impl;

import com.community.common.service.AbstractCurdService;
import com.community.dao.mapper.GroupMemberMapper;
import com.community.domain.model.db.GroupMemberDO;
import com.community.domain.request.GroupMemberRequest;
import com.community.domain.response.GroupMemberResponse;
import com.community.service.GroupMemberService;
import org.springframework.stereotype.Service;


@Service
public class GroupMemberServiceImpl extends AbstractCurdService<Long, GroupMemberRequest, GroupMemberDO, GroupMemberResponse, GroupMemberMapper>
        implements GroupMemberService {

}
