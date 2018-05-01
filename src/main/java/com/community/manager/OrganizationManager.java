/**
 * Created by wubiao on - 2018/04/29.
 */

package com.community.manager;

import com.community.common.constant.Constant;
import com.community.common.constant.ContentConstant;
import com.community.common.util.BeanUtils;
import com.community.common.util.ContentUtils;
import com.community.common.util.FutureUtils;
import com.community.dao.mapper.OrganizationMapper;
import com.community.domain.core.Page;
import com.community.domain.core.PaginationAble;
import com.community.domain.core.Response;
import com.community.domain.model.db.*;
import com.community.domain.request.*;
import com.community.domain.response.*;
import com.community.domain.session.LoginContext;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Service
public class OrganizationManager {

    @Autowired
    private OrganizationMapper organizationMapper;
    @Autowired
    private ContentManager contentManager;
    @Autowired
    private ActivityManager activityManager;
    @Autowired
    private OrganizationMemberManager organizationMemberManager;
    @Autowired
    private TagManager tagManager;

    public Future<Page<OrganizationDO>> listPageAsync(OrganizationRequest request) {
        return CompletableFuture.supplyAsync(() -> organizationMapper.listPage(request));
    }

    public Response<OrganizationResponse> organizationDetail(OrganizationRequest request) {
        Long id = request.getId();
        //帖子详情
        OrganizationDO organizationDO = organizationMapper.getByKey(id);
        if (null == organizationDO) {
            return Response.fail("组织不存在");
        }
        Long orgId = organizationDO.getId();
        Long userId = LoginContext.getUserId();
        //置顶帖子
        ContentRequest contentRequest = new ContentRequest();
        contentRequest.setTop(ContentConstant.TopEnum.TOP_YES.getCode());
        PaginationAble pagination = new PaginationAble();
        pagination.setPage(Constant.TOP_DEFAULT_NUM);
        contentRequest.setPagination(pagination);
        Future<Page<ContentDO>> contentPageFuture = contentManager.listPageAsync(contentRequest);
        //活动
        ActivityRequest activityRequest = new ActivityRequest();
        pagination = new PaginationAble();
        pagination.setPage(Constant.TOP_DEFAULT_NUM);
        activityRequest.setPagination(pagination);
        Future<Page<ActivityDO>> activityPageFuture = activityManager.listPageAsync(activityRequest);
        //标签
        TagRequest tagRequest = new TagRequest();
        tagRequest.setBizId(orgId);
        tagRequest.setType(Constant.TypeEnum.ORG.getCode());
        tagRequest.setIsShow(Constant.ShowEnum.SHOW.getCode());
        Future<Page<TagDO>> tagPageFuture = tagManager.listPageAsync(tagRequest);
        //当前人角色
        OrganizationMemberDO organizationMemberDO = null;
        if (LoginContext.isLogin()) {
            OrganizationMemberRequest memberRequest = new OrganizationMemberRequest();
            memberRequest.setOrgId(orgId);
            memberRequest.setUserId(userId);
            Future<OrganizationMemberDO> organizationMemberDOFuture = organizationMemberManager.getOrgMemberAsync(memberRequest);
            organizationMemberDO = FutureUtils.get(organizationMemberDOFuture);
        }
        //置顶帖子
        Page<ContentDO> contentDOPage = FutureUtils.get(contentPageFuture);
        List<ContentDO> contentDOList = Optional.ofNullable(contentDOPage).flatMap(Page::getDataOptional).orElse(null);
        //活动
        Page<ActivityDO> activityDOPage = FutureUtils.get(activityPageFuture);
        List<ActivityDO> activityDOList = Optional.ofNullable(activityDOPage).flatMap(Page::getDataOptional).orElse(null);
        //标签
        Page<TagDO> tagDOPage = FutureUtils.get(tagPageFuture);
        List<TagDO> tagDOList = Optional.ofNullable(tagDOPage).flatMap(Page::getDataOptional).orElse(null);

        //返回对象
        OrganizationResponse organizationResponse = BeanUtils.copyProperties(organizationDO, OrganizationResponse.class);
        //转化返回值
        List<ContentResponse> contentResponseList = BeanUtils.list2list(contentDOList, ContentResponse.class);
        ContentUtils.extractSummaryAndCalculateImgNum(contentResponseList);
        List<ActivityResponse> activityResponseList = BeanUtils.list2list(activityDOList, ActivityResponse.class);
        List<TagResponse> tagResponseList = BeanUtils.list2list(activityDOList, TagResponse.class);
        OrganizationMemberResponse organizationMemberResponse = BeanUtils.copyProperties(organizationMemberDO, OrganizationMemberResponse.class);
        //设置相关返回值
        organizationResponse.setContentList(contentResponseList);
        organizationResponse.setActivityList(activityResponseList);
        organizationResponse.setTagList(tagResponseList);
        organizationResponse.setOrganizationMember(organizationMemberResponse);
        return Response.success(organizationResponse);
    }

    public static void main(String[] args) {
        Page<ContentDO> contentDOPage = new Page<>();
        contentDOPage.setData(Lists.newArrayList(new ContentDO()));

        List<ContentDO> contentDOList = Optional.ofNullable(contentDOPage).flatMap(Page::getDataOptional).orElse(null);
        System.out.println(contentDOList);
    }
}
