/**
 * Created by wubiao on - 2018/04/29.
 */

package com.community.manager;

import com.community.common.enums.ApiHttpStatus;
import com.community.common.util.BeanUtils;
import com.community.common.util.FutureUtils;
import com.community.convert.ContentConvert;
import com.community.dao.mapper.ActivityMapper;
import com.community.dao.mapper.ContentMapper;
import com.community.dao.mapper.OrganizationMapper;
import com.community.dao.mapper.UserInfoMapper;
import com.community.domain.core.Page;
import com.community.domain.core.Response;
import com.community.domain.model.db.*;
import com.community.domain.request.ContentRequest;
import com.community.domain.request.OrganizationMemberRequest;
import com.community.domain.response.*;
import com.community.domain.session.LoginContext;
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

import static com.community.common.constant.Constant.*;


@Service
public class ContentManager {

    @Autowired
    private ContentMapper contentMapper;
    @Autowired
    private OrganizationMapper organizationMapper;
    @Autowired
    private ActivityMapper activityMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private UserInfoManager userInfoManager;
    @Autowired
    private ActivityManager activityManager;
    @Autowired
    private OrganizationMemberManager organizationMemberManager;

    public Future<Page<ContentDO>> listPageAsync(ContentRequest request) {
        return CompletableFuture.supplyAsync(() -> contentMapper.listPage(request));
    }

    /**
     * 查询帖子
     *
     * @param request
     * @return
     */
    public Response<List<ContentResponse>> listContentPage(ContentRequest request) {
        if (null == request) {
            request = new ContentRequest();
        }
        //查询帖子
        Page<ContentDO> contentDOPage = contentMapper.listPage(request);
        List<ContentDO> contentDOList = Optional.ofNullable(contentDOPage).flatMap(Page::getData).orElse(null);
        List<ContentResponse> contentResponseList = null;
        if (CollectionUtils.isNotEmpty(contentDOList)) {
            contentResponseList = BeanUtils.list2list(contentDOList, ContentResponse.class);
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
            //设置组织、活动、发帖人
            ContentConvert.setOrgActivityAndUserInfo(contentResponseList, organizationDOMap, activityDOMap, userInfoDOMap);
        }
        return Response.success(contentResponseList);
    }

    public ContentResponse contentById(Long id) {
        if (null == id) {
            return null;
        }
        ContentDO contentDO = contentMapper.getByKey(id);
        if (null == contentDO || IsDeleteEnum.YES.getCode().equals(contentDO.getStatus())) {
            return null;
        }
        ContentResponse contentResponse = BeanUtils.copyProperties(contentDO, ContentResponse.class);
        //查找发布者
        UserInfoDO userInfoDO = userInfoMapper.getByKey(contentResponse.getId());
        UserInfoResponse userInfoResponse = BeanUtils.copyProperties(userInfoDO, UserInfoResponse.class);
        contentResponse.setUserInfo(userInfoResponse);
        return contentResponse;
    }

    public Response<ContentResponse> contentDetail(ContentRequest contentRequest) {
        ContentResponse contentResponse = null;
        if (null == contentRequest || null == contentRequest.getId() || null == (this.contentById(contentRequest.getId()))) {
            return Response.fail(ApiHttpStatus.ARGUMENT_ERROR.getCode(), "id不能为空");
        }
        Long bizId = contentResponse.getBizId();
        Long bizChildId = contentResponse.getBizChildId();
        //组织、活动
        if (TypeEnum.ORG.getCode().equals(contentResponse.getType()) || TypeEnum.ACTIVITY.getCode().equals(contentResponse.getType())) {
            //公共活动没有组织
            if (!PUBLIC_ACTIVITY_ORG_ID.equals(bizId)) {
                OrganizationDO organizationDO = organizationMapper.getByKey(bizId);
                OrganizationResponse organizationResponse = BeanUtils.copyProperties(organizationDO, OrganizationResponse.class);
                contentResponse.setOrganization(organizationResponse);
                //查询圈子身份
                OrganizationMemberRequest memberRequest = new OrganizationMemberRequest();
                memberRequest.setUserId(LoginContext.getUserId());
                memberRequest.setOrgId(bizId);
                OrganizationMemberDO organizationMemberDO = organizationMemberManager.getOrgMember(memberRequest);
                OrganizationMemberResponse organizationMemberResponse = BeanUtils.copyProperties(organizationMemberDO, OrganizationMemberResponse.class);
                contentResponse.setOrganizationMember(organizationMemberResponse);
            }
            //活动
            if (TypeEnum.ACTIVITY.getCode().equals(contentResponse.getType())) {
                ActivityDO activityDO = activityMapper.getByKey(bizChildId);
                ActivityResponse activityResponse = BeanUtils.copyProperties(activityDO, ActivityResponse.class);
                contentResponse.setActivity(activityResponse);
            }
            //回答
        } else if (TypeEnum.ANSWER.getCode().equals(contentResponse.getType())) {
            //查询问题
            ContentResponse questionContentResponse = contentById(bizId);
            contentResponse.setQuestionContent(questionContentResponse);
        }
        return Response.success(contentResponse);
    }


}
