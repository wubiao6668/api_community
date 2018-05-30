/**
 * Created by wubiao on - 2018/05/30.
 */

package com.community.service;

import com.community.common.service.CurdServiceAble;
import com.community.domain.model.db.UserChatInfoDO;
import com.community.domain.request.UserChatInfoRequest;
import com.community.domain.response.UserChatInfoResponse;

public interface UserChatInfoService extends CurdServiceAble<Long, UserChatInfoRequest, UserChatInfoDO, UserChatInfoResponse> {

}
