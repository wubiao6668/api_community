/**
 * Created by wubiao on - 2018/05/30.
 */

package com.community.service;

import com.community.common.service.CurdServiceAble;
import com.community.domain.model.db.GroupChatInfoDO;
import com.community.domain.request.GroupChatInfoRequest;
import com.community.domain.response.GroupChatInfoResponse;

public interface GroupChatInfoService extends CurdServiceAble<Long, GroupChatInfoRequest, GroupChatInfoDO, GroupChatInfoResponse> {

}
