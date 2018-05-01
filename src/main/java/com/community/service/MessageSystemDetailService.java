/**
 * Created by wubiao on - 2018/04/29.
 */

package com.community.service;

import com.community.common.service.CurdServiceAble;
import com.community.domain.model.db.MessageSystemDetailDO;
import com.community.domain.request.MessageSystemDetailRequest;
import com.community.domain.response.MessageSystemDetailResponse;

public interface MessageSystemDetailService extends CurdServiceAble<Long, MessageSystemDetailRequest, MessageSystemDetailDO, MessageSystemDetailResponse> {

}
