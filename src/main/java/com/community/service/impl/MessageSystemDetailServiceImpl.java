/**
 * Created by wubiao on - 2018/04/29.
 */

package com.community.service.impl;

import com.community.common.service.AbstractCurdService;
import com.community.dao.mapper.MessageSystemDetailMapper;
import com.community.domain.model.db.MessageSystemDetailDO;
import com.community.domain.request.MessageSystemDetailRequest;
import com.community.domain.response.MessageSystemDetailResponse;
import com.community.service.MessageSystemDetailService;
import org.springframework.stereotype.Service;


@Service
public class MessageSystemDetailServiceImpl extends AbstractCurdService<Long, MessageSystemDetailRequest, MessageSystemDetailDO, MessageSystemDetailResponse, MessageSystemDetailMapper>
        implements MessageSystemDetailService {

}
