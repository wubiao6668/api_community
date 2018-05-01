/**
 * Created by wubiao on - 2018/04/29.
 */

package com.community.service;

import com.community.common.service.CurdServiceAble;
import com.community.domain.model.db.CommentDO;
import com.community.domain.request.CommentRequest;
import com.community.domain.response.CommentResponse;

public interface CommentService extends CurdServiceAble<Long, CommentRequest, CommentDO, CommentResponse> {

}
