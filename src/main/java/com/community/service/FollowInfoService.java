/**
 * Created by wubiao on - 2018/04/29.
 */

package com.community.service;

import com.community.common.service.CurdServiceAble;
import com.community.domain.model.db.FollowInfoDO;
import com.community.domain.request.FollowInfoRequest;
import com.community.domain.response.FollowInfoResponse;

public interface FollowInfoService extends CurdServiceAble<Long, FollowInfoRequest, FollowInfoDO, FollowInfoResponse> {

}
