/**
 * Created by wubiao on - 2018/04/29.
 */

package com.community.service;

import com.community.common.service.CurdServiceAble;
import com.community.domain.model.db.FollowUserDO;
import com.community.domain.request.FollowUserRequest;
import com.community.domain.response.FollowUserResponse;

public interface FollowUserService extends CurdServiceAble<Long, FollowUserRequest, FollowUserDO, FollowUserResponse> {

}
