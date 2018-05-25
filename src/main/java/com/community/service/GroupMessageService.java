/**
 * Created by wubiao on - 2018/05/25.
 */

package com.community.service;

import com.community.common.service.CurdServiceAble;
import com.community.domain.model.db.GroupMessageDO;
import com.community.domain.request.GroupMessageRequest;
import com.community.domain.response.GroupMessageResponse;

public interface GroupMessageService extends CurdServiceAble<Long, GroupMessageRequest, GroupMessageDO, GroupMessageResponse> {

}
