/**
 * Created by wubiao on - 2018/04/29.
 */

package com.community.service;

import com.community.common.service.CurdServiceAble;
import com.community.domain.model.db.MessageSystemDO;
import com.community.domain.request.MessageSystemRequest;
import com.community.domain.response.MessageSystemResponse;

public interface MessageSystemService extends CurdServiceAble<Long, MessageSystemRequest, MessageSystemDO, MessageSystemResponse> {

}
