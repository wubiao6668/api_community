/**
 * Created by wubiao on - 2018/04/29.
 */

package com.community.service;

import com.community.common.service.CurdServiceAble;
import com.community.domain.model.db.ReplyDO;
import com.community.domain.request.ReplyRequest;
import com.community.domain.response.ReplyResponse;

public interface ReplyService extends CurdServiceAble<Long, ReplyRequest, ReplyDO, ReplyResponse> {

}
