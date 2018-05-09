/**
 * Created by wubiao on - 2018/04/29.
 */

package com.community.manager;

import com.community.common.constant.Constant;
import com.community.common.constant.ContentConstant;
import com.community.common.constant.OrganizationConstant.StatusEnum;
import com.community.common.enums.ApiHttpStatus;
import com.community.common.util.BeanUtils;
import com.community.common.util.ContentUtils;
import com.community.common.util.FutureUtils;
import com.community.dao.mapper.OrganizationMapper;
import com.community.domain.core.Page;
import com.community.domain.core.PaginationAble;
import com.community.domain.core.Response;
import com.community.domain.model.db.*;
import com.community.domain.request.*;
import com.community.domain.response.OrganizationMemberResponse;
import com.community.domain.response.OrganizationResponse;
import com.community.domain.response.vo.*;
import com.community.domain.session.LoginContext;
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

    /**
     * 组织详情页
     *
     * @param request
     * @return
     */
    public Response<OrganizationResponse> organizationDetail(OrganizationRequest request) {
        if (null == request || null == request.getId()) {
            return Response.fail(ApiHttpStatus.ARGUMENT_ERROR.getCode(), "id 不能为空");
        }
        Long id = request.getId();
        //组织详情
        OrganizationDO organizationDO = organizationMapper.getByKey(id);
        if (null == organizationDO) {
            return Response.fail(ApiHttpStatus.NOT_FOUND.getCode(), "组织不存在");
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
        Integer role = null;
        if (LoginContext.isLogin()) {
            OrganizationMemberRequest memberRequest = new OrganizationMemberRequest();
            memberRequest.setOrgId(orgId);
            memberRequest.setUserId(userId);
            Future<OrganizationMemberDO> organizationMemberDOFuture = organizationMemberManager.getOrgMemberAsync(memberRequest);
            OrganizationMemberDO organizationMemberDO = FutureUtils.get(organizationMemberDOFuture);
            role = Optional.ofNullable(organizationMemberDO).map(a -> a.getRole()).orElse(null);
        }
        //置顶帖子
        Page<ContentDO> contentDOPage = FutureUtils.get(contentPageFuture);
        List<ContentDO> contentDOList = Optional.ofNullable(contentDOPage).flatMap(Page::getData).orElse(null);
        //活动
        Page<ActivityDO> activityDOPage = FutureUtils.get(activityPageFuture);
        List<ActivityDO> activityDOList = Optional.ofNullable(activityDOPage).flatMap(Page::getData).orElse(null);
        //标签
        Page<TagDO> tagDOPage = FutureUtils.get(tagPageFuture);
        List<TagDO> tagDOList = Optional.ofNullable(tagDOPage).flatMap(Page::getData).orElse(null);
        //返回对象
        OrganizationResponse organizationResponse = new OrganizationResponse();
        //转化返回值
        List<ContentVO> contentVOList = BeanUtils.list2list(contentDOList, ContentVO.class);
        ContentUtils.extractSummary(contentVOList);
        List<ActivityVO> activityVOList = BeanUtils.list2list(activityDOList, ActivityVO.class);
        List<TagVO> tagVOList = BeanUtils.list2list(tagDOList, TagVO.class);
        OrganizationVO organizationVO = BeanUtils.copyProperties(organizationDO, OrganizationVO.class);
        //设置相关返回值
        organizationVO.setContentList(contentVOList);
        organizationVO.setActivityList(activityVOList);
        organizationVO.setTagList(tagVOList);
        organizationVO.setRole(role);
        return Response.success(organizationResponse);
    }

    /**
     * 组织介绍
     *
     * @param request
     * @return
     */
    public Response<OrganizationResponse> organizationIntroduce(OrganizationRequest request) {
        if (null == request || null == request.getId()) {
            return Response.fail(ApiHttpStatus.ARGUMENT_ERROR.getCode(), "id 不能为空");
        }
        Long id = request.getId();
        //组织详情
        OrganizationDO organizationDO = organizationMapper.getByKey(id);
        if (null == organizationDO) {
            return Response.fail(ApiHttpStatus.NOT_FOUND.getCode(), "组织不存在");
        }
        OrganizationVO organizationVO = BeanUtils.copyProperties(organizationDO, OrganizationVO.class);
        //预览成员
        OrganizationMemberRequest organizationMemberRequest = new OrganizationMemberRequest();
        organizationMemberRequest.setOrgId(id);
        organizationMemberRequest.setStatus(StatusEnum.FOLLOW.getCode());
        organizationMemberRequest.getPagination().setPageSize(Constant.DEFAULT_NUM);
        //只看普通的成员
        organizationMemberRequest.setRole(Constant.RoleEnum.ORDINARY.getCode());
        Response<OrganizationMemberResponse> ordinaryMemberResponse = organizationMemberManager.listPage(organizationMemberRequest);
        List<OrganizationMemberVO> orgMemberList = Optional.ofNullable(ordinaryMemberResponse).map(a -> a.getData()).map(b -> b.getOrgMemberList()).orElse(null);
        organizationVO.setPreViewMemberList(orgMemberList);
        //当前登录人是否关注圈子
        Long loginUserId = LoginContext.getUserId();
        if (null == loginUserId) {
            OrganizationMemberRequest organizationFollowRequest = new OrganizationMemberRequest();
            organizationFollowRequest.setOrgId(id);
            organizationFollowRequest.setUserId(loginUserId);
            organizationFollowRequest.setStatus(StatusEnum.FOLLOW.getCode());
            OrganizationMemberDO organizationMemberDO = organizationMemberManager.getOrgMember(organizationFollowRequest);
            if (null != organizationMemberDO) {
                organizationVO.setRole(organizationMemberDO.getRole());
                organizationVO.setIsFollow(true);
            }
        }
        //返回结果
        OrganizationResponse organizationResponse = new OrganizationResponse();
        organizationResponse.setOrganization(organizationVO);
        return Response.success(organizationResponse);
    }

    public static void main(String[] args) {
        Page<ContentDO> contentDOPage = new Page<>();
//        contentDOPage.setData(Lists.newArrayList(new ContentDO()));

        List<ContentDO> contentDOList = Optional.ofNullable(contentDOPage).flatMap(Page::getData).orElse(null);
        System.out.println(contentDOList);
    }
}
