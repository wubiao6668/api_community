/**
 * Created by wubiao on - 2018/04/29.
 */

package com.community.service.impl;

import com.community.common.service.AbstractCurdService;
import com.community.dao.mapper.ReplyMapper;
import com.community.domain.model.db.ReplyDO;
import com.community.domain.request.ReplyRequest;
import com.community.domain.response.ReplyResponse;
import com.community.service.ReplyService;
import org.springframework.stereotype.Service;


@Service
public class ReplyServiceImpl extends AbstractCurdService<Long, ReplyRequest, ReplyDO, ReplyResponse, ReplyMapper>
        implements ReplyService {

}
