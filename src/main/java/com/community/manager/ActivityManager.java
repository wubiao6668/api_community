/**
 * Created by wubiao on - 2018/04/29.
 */

package com.community.manager;

import com.community.common.enums.ApiHttpStatus;
import com.community.common.util.BeanUtils;
import com.community.dao.mapper.ActivityMapper;
import com.community.dao.mapper.UserInfoMapper;
import com.community.domain.core.Page;
import com.community.domain.core.Response;
import com.community.domain.model.db.ActivityDO;
import com.community.domain.model.db.UserInfoDO;
import com.community.domain.request.ActivityRequest;
import com.community.domain.response.ActivityResponse;
import com.community.domain.response.UserInfoResponse;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import static com.community.common.constant.Constant.IsDeleteEnum;

@Service
public class ActivityManager {

    @Autowired
    private ActivityMapper activityMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    public Future<Page<ActivityDO>> listPageAsync(ActivityRequest request) {
        return CompletableFuture.supplyAsync(() -> activityMapper.listPage(request));
    }

    Future<Map<Long, ActivityDO>> getActivityByIdsAsync(Set<Long> activityIdSets) {
        if (CollectionUtils.isEmpty(activityIdSets)) {
            return null;
        }
        return CompletableFuture.supplyAsync(() -> activityMapper.getByKeys(activityIdSets));
    }

    public Response<ActivityResponse> activityDetail(ActivityRequest request) {
        if (null == request || null == request.getId()) {
            Response.fail(ApiHttpStatus.ARGUMENT_ERROR.getCode(), "id 不能为空");
        }
        Long id = request.getId();
        ActivityDO activityDO = activityMapper.getByKey(id);
        if (null == activityDO || IsDeleteEnum.YES.equals(activityDO.getIsDelete())) {
            Response.fail(ApiHttpStatus.NOT_FOUND.getCode(), "活动不存在");
        }
        ActivityResponse activityResponse = BeanUtils.copyProperties(activityDO, ActivityResponse.class);
        //查询发起人
        Long originatorUserId = activityDO.getOriginatorUserId();
        UserInfoDO userInfoDO = userInfoMapper.getByKey(originatorUserId);
        UserInfoResponse userInfoResponse = BeanUtils.copyProperties(userInfoDO, UserInfoResponse.class);
        activityResponse.setOriginatorUserInfo(userInfoResponse);
        return Response.success(activityResponse);
    }
}
