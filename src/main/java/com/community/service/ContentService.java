/**
 * Created by wubiao on - 2018/04/29.
 */

package com.community.service;

import com.community.common.service.CurdServiceAble;
import com.community.domain.model.db.ContentDO;
import com.community.domain.request.ContentRequest;
import com.community.domain.response.ContentResponse;

public interface ContentService extends CurdServiceAble<Long, ContentRequest, ContentDO, ContentResponse> {

}
