/**
 * Created by wubiao on - 2018/05/25.
 */

package com.community.service;

import com.community.common.service.CurdServiceAble;
import com.community.domain.model.db.GroupInfoDO;
import com.community.domain.request.GroupInfoRequest;
import com.community.domain.response.GroupInfoResponse;

public interface GroupInfoService extends CurdServiceAble<Long, GroupInfoRequest, GroupInfoDO, GroupInfoResponse> {

}
