/**
 * Created by wubiao on - 2018/05/25.
 */

package com.community.service;

import com.community.common.service.CurdServiceAble;
import com.community.domain.model.db.UserMessageDO;
import com.community.domain.request.UserMessageRequest;
import com.community.domain.response.UserMessageResponse;

public interface UserMessageService extends CurdServiceAble<Long, UserMessageRequest, UserMessageDO, UserMessageResponse> {

}
