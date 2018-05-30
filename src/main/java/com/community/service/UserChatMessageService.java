/**
 * Created by wubiao on - 2018/05/30.
 */

package com.community.service;

import com.community.common.service.CurdServiceAble;
import com.community.domain.model.db.UserChatMessageDO;
import com.community.domain.request.UserChatMessageRequest;
import com.community.domain.response.UserChatMessageResponse;

public interface UserChatMessageService extends CurdServiceAble<Long, UserChatMessageRequest, UserChatMessageDO, UserChatMessageResponse> {

}
