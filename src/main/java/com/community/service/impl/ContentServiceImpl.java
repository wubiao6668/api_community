/**
 * Created by wubiao on - 2018/04/29.
 */

package com.community.service.impl;

import com.community.common.service.AbstractCurdService;
import com.community.dao.mapper.ContentMapper;
import com.community.domain.model.db.ContentDO;
import com.community.domain.request.ContentRequest;
import com.community.domain.response.ContentResponse;
import com.community.service.ContentService;
import org.springframework.stereotype.Service;


@Service
public class ContentServiceImpl extends AbstractCurdService<Long, ContentRequest, ContentDO, ContentResponse, ContentMapper>
        implements ContentService {

}
