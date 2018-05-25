/**
 * Created by wubiao on - 2018/05/25.
 */

package com.community.service;

import com.community.common.service.CurdServiceAble;
import com.community.domain.model.db.UserMessageDetailDO;
import com.community.domain.request.UserMessageDetailRequest;
import com.community.domain.response.UserMessageDetailResponse;

public interface UserMessageDetailService extends CurdServiceAble<Long, UserMessageDetailRequest, UserMessageDetailDO, UserMessageDetailResponse> {

}
