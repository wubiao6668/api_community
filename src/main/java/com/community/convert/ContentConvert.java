package com.community.convert;

import com.community.common.constant.Constant;
import com.community.common.util.BeanUtils;
import com.community.common.util.UserUtils;
import com.community.domain.model.db.ActivityDO;
import com.community.domain.model.db.OrganizationDO;
import com.community.domain.model.db.UserInfoDO;
import com.community.domain.response.vo.ActivityVO;
import com.community.domain.response.vo.ContentVO;
import com.community.domain.response.vo.OrganizationVO;
import com.community.domain.response.vo.UserInfoVO;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ContentConvert {

    public static void setOrgActivityAndUserInfo(List<ContentVO> contentVOList,
                                                 Map<Long, OrganizationDO> organizationDOMap,
                                                 Map<Long, ActivityDO> activityDOMap,
                                                 Map<Long, UserInfoDO> userInfoDOMap) {
        if (CollectionUtils.isEmpty(contentVOList)) {
            return;
        }
        contentVOList.forEach(contentVO -> {
            //发帖人id
            UserInfoVO userInfoVO = UserUtils.getUserInfoVO(userInfoDOMap, contentVO.getUserId());
            contentVO.setUserInfo(userInfoVO);
            //组织
            if (Constant.TypeEnum.ORG.getCode().equals(contentVO.getType()) || Constant.TypeEnum.ACTIVITY.getCode().equals(contentVO.getType())) {
                OrganizationDO organizationDO = Optional.ofNullable(organizationDOMap).map(a -> a.get(contentVO.getBizId())).orElse(null);
                OrganizationVO organizationVO = BeanUtils.copyProperties(organizationDO, OrganizationVO.class);
                contentVO.setOrganization(organizationVO);
            }
            //活动
            if (Constant.TypeEnum.ACTIVITY.getCode().equals(contentVO.getType())) {
                ActivityDO activityDO = Optional.ofNullable(activityDOMap).map(a -> a.get(contentVO.getBizId())).orElse(null);
                ActivityVO activityVO = BeanUtils.copyProperties(activityDO, ActivityVO.class);
                contentVO.setActivity(activityVO);
            }
        });
        return;
    }


}
