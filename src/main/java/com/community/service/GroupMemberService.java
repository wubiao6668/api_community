/**
 * Created by wubiao on - 2018/05/25.
 */

package com.community.service;

import com.community.common.service.CurdServiceAble;
import com.community.domain.model.db.GroupMemberDO;
import com.community.domain.request.GroupMemberRequest;
import com.community.domain.response.GroupMemberResponse;

public interface GroupMemberService extends CurdServiceAble<Long, GroupMemberRequest, GroupMemberDO, GroupMemberResponse> {

}
