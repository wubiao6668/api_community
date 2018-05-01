/**
 * Created by wubiao on - 2018/05/01.
 */

package com.community.service.impl;

import com.community.common.service.AbstractCurdService;
import com.community.dao.mapper.UserInfoMapper;
import com.community.domain.model.db.UserInfoDO;
import com.community.domain.request.UserInfoRequest;
import com.community.domain.response.UserInfoResponse;
import com.community.service.UserInfoService;
import org.springframework.stereotype.Service;


@Service
public class UserInfoServiceImpl extends AbstractCurdService<Long, UserInfoRequest, UserInfoDO, UserInfoResponse, UserInfoMapper>
        implements UserInfoService {

}
