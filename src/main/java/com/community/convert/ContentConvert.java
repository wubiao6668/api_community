package com.community.convert;

import com.community.common.constant.Constant;
import com.community.common.util.BeanUtils;
import com.community.domain.model.db.ActivityDO;
import com.community.domain.model.db.OrganizationDO;
import com.community.domain.model.db.UserInfoDO;
import com.community.domain.response.ActivityResponse;
import com.community.domain.response.ContentResponse;
import com.community.domain.response.OrganizationResponse;
import com.community.domain.response.UserInfoResponse;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;
import java.util.Map;

public class ContentConvert {

    public static void setOrgActivityAndUserInfo(List<ContentResponse> contentResponseList,
                                                 Map<Long, OrganizationDO> organizationDOMap,
                                                 Map<Long, ActivityDO> activityDOMap,
                                                 Map<Long, UserInfoDO> userInfoDOMap) {
        if (CollectionUtils.isEmpty(contentResponseList)) {
            return;
        }

        contentResponseList.forEach(contentResponse -> {
            //发帖人id
            UserInfoDO userInfoDO = null != userInfoDOMap && null != (userInfoDO = userInfoDOMap.get(contentResponse.getUserId())) ? userInfoDO : null;
            if (null != userInfoDO) {
                UserInfoResponse userInfoResponse = BeanUtils.copyProperties(userInfoDO, UserInfoResponse.class);
                contentResponse.setUserInfo(userInfoResponse);
            }
            //组织
            if (Constant.TypeEnum.ORG.getCode().equals(contentResponse.getType()) || Constant.TypeEnum.ACTIVITY.getCode().equals(contentResponse.getType())) {
                OrganizationDO organizationDO = null != organizationDOMap && null != (organizationDO = organizationDOMap.get(contentResponse.getBizId())) ? organizationDO : null;
                if (null != organizationDO) {
                    OrganizationResponse organizationResponse = BeanUtils.copyProperties(organizationDO, OrganizationResponse.class);
                    contentResponse.setOrganization(organizationResponse);
                }
            }
            //活动
            if (Constant.TypeEnum.ACTIVITY.getCode().equals(contentResponse.getType())) {
                ActivityDO activityDO = null != activityDOMap && null != (activityDO = activityDOMap.get(contentResponse.getBizId())) ? activityDO : null;
                if (null != activityDO) {
                    ActivityResponse activityResponse = BeanUtils.copyProperties(activityDO, ActivityResponse.class);
                    contentResponse.setActivity(activityResponse);
                }
            }
        });
        return;
    }


}
