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
import com.community.domain.core.Pagination;
import com.community.domain.core.Response;
import com.community.domain.model.db.*;
import com.community.domain.request.ContentRequest;
import com.community.domain.request.OrganizationMemberRequest;
import com.community.domain.response.ContentResponse;
import com.community.domain.response.vo.*;
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
    public Response<ContentResponse> listContentPage(ContentRequest request) {
        if (null == request) {
            request = new ContentRequest();
        }
        ContentResponse contentResponse = new ContentResponse();
        //查询帖子
        Page<ContentDO> contentDOPage = contentMapper.listPage(request);
        List<ContentDO> contentDOList = Optional.ofNullable(contentDOPage).flatMap(Page::getData).orElse(null);
        Pagination pagination = Optional.ofNullable(contentDOPage).flatMap(Page::getPagination).orElse(null);
        Boolean hasMore = Optional.ofNullable(contentDOPage).map(Page::getHasMore).orElse(null);
        List<ContentVO> contentVOList = null;
        if (CollectionUtils.isNotEmpty(contentDOList)) {
            contentVOList = BeanUtils.list2list(contentDOList, ContentVO.class);
            //组织id
            Set<Long> orgIdSet = Sets.newHashSet();
            //活动id
            Set<Long> activityIdSet = Sets.newHashSet();
            //发帖人id
            Set<Long> userIdSet = Sets.newHashSet();
            contentVOList.forEach(contentVO -> {
                //组织
                if (TypeEnum.ORG.getCode().equals(contentVO.getType()) || TypeEnum.ACTIVITY.getCode().equals(contentVO.getType())) {
                    orgIdSet.add(contentVO.getBizId());
                }
                //活动
                if (TypeEnum.ACTIVITY.getCode().equals(contentVO.getType())) {
                    activityIdSet.add(contentVO.getBizChildId());
                }
                //发帖人id
                userIdSet.add(contentVO.getUserId());
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
            ContentConvert.setOrgActivityAndUserInfo(contentVOList, organizationDOMap, activityDOMap, userInfoDOMap);
        }
        contentResponse.setContentList(contentVOList);
        contentResponse.setPagination(pagination);
        contentResponse.setHasMore(hasMore);
        return Response.success(contentResponse);
    }

    public ContentVO contentById(Long id) {
        if (null == id) {
            return null;
        }
        ContentDO contentDO = contentMapper.getByKey(id);
        if (null == contentDO || IsDeleteEnum.YES.getCode().equals(contentDO.getStatus())) {
            return null;
        }
        ContentVO contentVO = BeanUtils.copyProperties(contentDO, ContentVO.class);
        //查找发布者
        UserInfoVO userInfoVO = userInfoManager.getUserByIdIfNotExistReturnDefault(contentDO.getUserId());
        contentVO.setUserInfo(userInfoVO);
        return contentVO;
    }

    public Response<ContentResponse> contentDetail(ContentRequest contentRequest) {
        ContentResponse contentResponse = new ContentResponse();
        if (null == contentRequest || null == contentRequest.getId()) {
            return Response.fail(ApiHttpStatus.ARGUMENT_ERROR.getCode(), "id不能为空");
        }
        ContentVO contentVO = this.contentById(contentRequest.getId());
        if (null == contentVO) {
            return Response.fail(ApiHttpStatus.NOT_FOUND.getCode(), "内容已被删除");
        }
        Long bizId = contentVO.getBizId();
        Long bizChildId = contentVO.getBizChildId();
        //组织、活动
        if (TypeEnum.ORG.getCode().equals(contentVO.getType()) || TypeEnum.ACTIVITY.getCode().equals(contentVO.getType())) {
            //公共活动没有组织
            if (!PUBLIC_ACTIVITY_ORG_ID.equals(bizId)) {
                OrganizationDO organizationDO = organizationMapper.getByKey(bizId);
                OrganizationVO organizationVO = BeanUtils.copyProperties(organizationDO, OrganizationVO.class);
                contentVO.setOrganization(organizationVO);
                //查询圈子身份
                OrganizationMemberRequest memberRequest = new OrganizationMemberRequest();
                memberRequest.setUserId(LoginContext.getUserId());
                memberRequest.setOrgId(bizId);
                OrganizationMemberDO organizationMemberDO = organizationMemberManager.getOrgMember(memberRequest);
                OrganizationMemberVO organizationMemberVO = BeanUtils.copyProperties(organizationMemberDO, OrganizationMemberVO.class);
                contentVO.setOrganizationMember(organizationMemberVO);
            }
            //活动
            if (TypeEnum.ACTIVITY.getCode().equals(contentVO.getType())) {
                ActivityDO activityDO = activityMapper.getByKey(bizChildId);
                ActivityVO activityVO = BeanUtils.copyProperties(activityDO, ActivityVO.class);
                contentVO.setActivity(activityVO);
            }
            //回答
        } else if (TypeEnum.ANSWER.getCode().equals(contentVO.getType())) {
            //查询问题
            ContentVO questionContentVO = this.contentById(bizId);
            contentVO.setQuestionContent(questionContentVO);
        }
        contentResponse.setContent(contentVO);
        return Response.success(contentResponse);
    }


}
