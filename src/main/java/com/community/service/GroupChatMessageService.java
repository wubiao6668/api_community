/**
 * Created by wubiao on - 2018/05/30.
 */

package com.community.service;

import com.community.common.service.CurdServiceAble;
import com.community.domain.model.db.GroupChatMessageDO;
import com.community.domain.request.GroupChatMessageRequest;
import com.community.domain.response.GroupChatMessageResponse;

public interface GroupChatMessageService extends CurdServiceAble<Long, GroupChatMessageRequest, GroupChatMessageDO, GroupChatMessageResponse> {

}
