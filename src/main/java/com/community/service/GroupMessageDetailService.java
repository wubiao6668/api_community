/**
 * Created by wubiao on - 2018/05/25.
 */

package com.community.service;

import com.community.common.service.CurdServiceAble;
import com.community.domain.model.db.GroupMessageDetailDO;
import com.community.domain.request.GroupMessageDetailRequest;
import com.community.domain.response.GroupMessageDetailResponse;

public interface GroupMessageDetailService extends CurdServiceAble<Long, GroupMessageDetailRequest, GroupMessageDetailDO, GroupMessageDetailResponse> {

}
