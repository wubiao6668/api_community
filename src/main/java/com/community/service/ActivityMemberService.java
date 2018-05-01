/**
 * Created by wubiao on - 2018/04/30.
 */

package com.community.service;

import com.community.common.service.CurdServiceAble;
import com.community.domain.model.db.ActivityMemberDO;
import com.community.domain.request.ActivityMemberRequest;
import com.community.domain.response.ActivityMemberResponse;

public interface ActivityMemberService extends CurdServiceAble<Long, ActivityMemberRequest, ActivityMemberDO, ActivityMemberResponse> {

}
