/**
 * Created by wubiao on - 2018/04/29.
 */

package com.community.service.impl;

import com.community.common.service.AbstractCurdService;
import com.community.dao.mapper.LikeMapper;
import com.community.domain.model.db.LikeDO;
import com.community.domain.request.LikeRequest;
import com.community.domain.response.LikeResponse;
import com.community.service.LikeService;
import org.springframework.stereotype.Service;


@Service
public class LikeServiceImpl extends AbstractCurdService<Long, LikeRequest, LikeDO, LikeResponse, LikeMapper>
        implements LikeService {

}
