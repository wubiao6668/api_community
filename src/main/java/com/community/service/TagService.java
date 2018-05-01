/**
 * Created by wubiao on - 2018/04/29.
 */

package com.community.service;

import com.community.common.service.CurdServiceAble;
import com.community.domain.model.db.TagDO;
import com.community.domain.request.TagRequest;
import com.community.domain.response.TagResponse;

public interface TagService extends CurdServiceAble<Long, TagRequest, TagDO, TagResponse> {

}
