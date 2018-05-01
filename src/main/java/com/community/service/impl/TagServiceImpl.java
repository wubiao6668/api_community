/**
 * Created by wubiao on - 2018/04/29.
 */

package com.community.service.impl;

import com.community.common.service.AbstractCurdService;
import com.community.dao.mapper.TagMapper;
import com.community.domain.model.db.TagDO;
import com.community.domain.request.TagRequest;
import com.community.domain.response.TagResponse;
import com.community.service.TagService;
import org.springframework.stereotype.Service;


@Service
public class TagServiceImpl extends AbstractCurdService<Long, TagRequest, TagDO, TagResponse, TagMapper>
        implements TagService {

}
