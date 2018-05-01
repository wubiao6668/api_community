/**
 * Created by wubiao on - 2018/04/29.
 */

package com.community.service.impl;

import com.community.common.service.AbstractCurdService;
import com.community.dao.mapper.ActivityMapper;
import com.community.domain.model.db.ActivityDO;
import com.community.domain.request.ActivityRequest;
import com.community.domain.response.ActivityResponse;
import com.community.service.ActivityService;
import org.springframework.stereotype.Service;


@Service
public class ActivityServiceImpl extends AbstractCurdService<Long, ActivityRequest, ActivityDO, ActivityResponse, ActivityMapper>
        implements ActivityService {

}
