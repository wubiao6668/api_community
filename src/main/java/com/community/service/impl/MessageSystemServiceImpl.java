/**
 * Created by wubiao on - 2018/04/29.
 */

package com.community.service.impl;

import com.community.common.service.AbstractCurdService;
import com.community.dao.mapper.MessageSystemMapper;
import com.community.domain.model.db.MessageSystemDO;
import com.community.domain.request.MessageSystemRequest;
import com.community.domain.response.MessageSystemResponse;
import com.community.service.MessageSystemService;
import org.springframework.stereotype.Service;


@Service
public class MessageSystemServiceImpl extends AbstractCurdService<Long, MessageSystemRequest, MessageSystemDO, MessageSystemResponse, MessageSystemMapper>
        implements MessageSystemService {

}
