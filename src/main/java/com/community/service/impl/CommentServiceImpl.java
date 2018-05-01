/**
 * Created by wubiao on - 2018/04/29.
 */

package com.community.service.impl;

import com.community.common.service.AbstractCurdService;
import com.community.dao.mapper.CommentMapper;
import com.community.domain.model.db.CommentDO;
import com.community.domain.request.CommentRequest;
import com.community.domain.response.CommentResponse;
import com.community.service.CommentService;
import org.springframework.stereotype.Service;


@Service
public class CommentServiceImpl extends AbstractCurdService<Long, CommentRequest, CommentDO, CommentResponse, CommentMapper>
        implements CommentService {

}
