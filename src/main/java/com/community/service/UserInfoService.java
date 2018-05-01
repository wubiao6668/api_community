/**
 * Created by wubiao on - 2018/05/01.
 */

package com.community.service;

import com.community.common.service.CurdServiceAble;
import com.community.domain.model.db.UserInfoDO;
import com.community.domain.request.UserInfoRequest;
import com.community.domain.response.UserInfoResponse;

public interface UserInfoService extends CurdServiceAble<Long, UserInfoRequest, UserInfoDO, UserInfoResponse> {

}
