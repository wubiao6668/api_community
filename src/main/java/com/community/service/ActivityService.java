/**
 * Created by wubiao on - 2018/04/29.
 */

package com.community.service;

import com.community.common.service.CurdServiceAble;
import com.community.domain.model.db.ActivityDO;
import com.community.domain.request.ActivityRequest;
import com.community.domain.response.ActivityResponse;

public interface ActivityService extends CurdServiceAble<Long, ActivityRequest, ActivityDO, ActivityResponse> {

}
