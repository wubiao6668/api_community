/**
 * Created by wubiao on - 2018/04/29.
 */

package com.community.manager;

import com.community.common.util.BeanUtils;
import com.community.common.util.FutureUtils;
import com.community.dao.mapper.ContentMapper;
import com.community.dao.mapper.OrganizationMapper;
import com.community.domain.core.Page;
import com.community.domain.core.Response;
import com.community.domain.model.db.ActivityDO;
import com.community.domain.model.db.ContentDO;
import com.community.domain.model.db.OrganizationDO;
import com.community.domain.model.db.UserInfoDO;
import com.community.domain.request.ContentRequest;
import com.community.domain.response.ActivityResponse;
import com.community.domain.response.ContentResponse;
import com.community.domain.response.OrganizationResponse;
import com.community.domain.response.UserInfoResponse;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import static com.community.common.constant.Constant.TypeEnum;


@Service
public class ContentManager {

    @Autowired
    private ContentMapper contentMapper;
    @Autowired
    private OrganizationMapper organizationMapper;
    @Autowired
    private UserInfoManager userInfoManager;
    @Autowired
    private ActivityManager activityManager;

    public Future<Page<ContentDO>> listPageAsync(ContentRequest request) {
        return CompletableFuture.supplyAsync(() -> contentMapper.listPage(request));
    }

    public Response listContent(ContentRequest request) {
        //查询帖子
        Page<ContentDO> contentDOPage = contentMapper.listPage(request);
        List<ContentDO> contentDOList = Optional.ofNullable(contentDOPage).flatMap(Page::getData).orElse(null);
        if (CollectionUtils.isNotEmpty(contentDOList)) {
            List<ContentResponse> contentResponseList = BeanUtils.list2list(contentDOList, ContentResponse.class);
            //组织id
            Set<Long> orgIdSet = Sets.newHashSet();
            //活动id
            Set<Long> activityIdSet = Sets.newHashSet();
            //发帖人id
            Set<Long> userIdSet = Sets.newHashSet();
            contentResponseList.forEach(contentResponse -> {
                //组织
                if (TypeEnum.ORG.getCode().equals(contentResponse.getType()) || TypeEnum.ACTIVITY.getCode().equals(contentResponse.getType())) {
                    orgIdSet.add(contentResponse.getBizId());
                }
                //活动
                if (TypeEnum.ACTIVITY.getCode().equals(contentResponse.getType())) {
                    activityIdSet.add(contentResponse.getBizChildId());
                }
                //发帖人id
                userIdSet.add(contentResponse.getUserId());
            });
            //查询活动
            Future<Map<Long, ActivityDO>> activityFuture = activityManager.getActivityByIdsAsync(activityIdSet);
            //查询发帖人
            Future<Map<Long, UserInfoDO>> userInfoFuture = userInfoManager.getUserInfoByIdsAsync(userIdSet);
            //查询组织
            Map<Long, OrganizationDO> organizationDOMap = null;
            if (CollectionUtils.isNotEmpty(orgIdSet)) {
                organizationDOMap = organizationMapper.getByKeys(orgIdSet);
            }
            Map<Long, ActivityDO> activityDOMap = FutureUtils.get(activityFuture);
            Map<Long, UserInfoDO> userInfoDOMap = FutureUtils.get(userInfoFuture);

        }
        //发帖人
        //活动
        return null;
    }

    public List<ContentResponse> setOrgActivityAndUserInfo(List<ContentResponse> contentResponseList,
                                                           Map<Long, OrganizationDO> organizationDOMap,
                                                           Map<Long, ActivityDO> activityDOMap, Map<Long, UserInfoDO> userInfoDOMap) {
        if (CollectionUtils.isEmpty(contentResponseList)) {
            return contentResponseList;
        }


        contentResponseList.forEach(contentResponse -> {
            //发帖人id
            UserInfoDO userInfoDO = null != userInfoDOMap && null != (userInfoDO = userInfoDOMap.get(contentResponse.getUserId())) ? userInfoDO : null;
            if (null != userInfoDO) {
                UserInfoResponse userInfoResponse = BeanUtils.copyProperties(userInfoDO, UserInfoResponse.class);
                contentResponse.setUserInfo(userInfoResponse);
            }
            //组织
            if (TypeEnum.ORG.getCode().equals(contentResponse.getType()) || TypeEnum.ACTIVITY.getCode().equals(contentResponse.getType())) {
                OrganizationDO organizationDO = null != organizationDOMap && null != (organizationDO = organizationDOMap.get(contentResponse.getBizId())) ? organizationDO : null;
                if (null != organizationDO) {
                    OrganizationResponse organizationResponse = BeanUtils.copyProperties(organizationDO, OrganizationResponse.class);
                    contentResponse.setOrganization(organizationResponse);
                }
            }
            //活动
            if (TypeEnum.ACTIVITY.getCode().equals(contentResponse.getType())) {
                ActivityDO activityDO = null != activityDOMap && null != (activityDO = activityDOMap.get(contentResponse.getBizId())) ? activityDO : null;
                if (null != activityDO) {
                    ActivityResponse activityResponse = BeanUtils.copyProperties(activityDO, ActivityResponse.class);
                    contentResponse.setActivity(activityResponse);
                }
            }
        });
        return contentResponseList;
    }

}
