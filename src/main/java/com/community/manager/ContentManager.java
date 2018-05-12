/**
 * Created by wubiao on - 2018/04/29.
 */

package com.community.manager;

import com.community.common.enums.ApiHttpStatus;
import com.community.common.util.BeanUtils;
import com.community.common.util.FutureUtils;
import com.community.common.util.UserUtils;
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
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

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
            for (ContentVO contentVO : contentVOList) {
                //发帖人id
                userIdSet.add(contentVO.getUserId());
                //组织
                if (TypeEnum.ORG.getCode().equals(contentVO.getType()) || TypeEnum.ACTIVITY.getCode().equals(contentVO.getType())) {
                    orgIdSet.add(contentVO.getBizId());
                    continue;
                }
                //活动
                if (TypeEnum.ACTIVITY.getCode().equals(contentVO.getType())) {
                    activityIdSet.add(contentVO.getBizChildId());
                    continue;
                }
            }
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
            //如果是回答 是否查询 问答
            if (request.isQueryQuestion()) {
                fillQuestionContentFromAnswer(contentVOList);
            }
        }
        contentResponse.setContentList(contentVOList);
        contentResponse.setPagination(pagination);
        contentResponse.setHasMore(hasMore);
        return Response.success(contentResponse);
    }

    /**
     * 如果是问答需要填充问题
     *
     * @param contentVOList
     */
    public void fillQuestionContentFromAnswer(List<ContentVO> contentVOList) {
        if (CollectionUtils.isNotEmpty(contentVOList)) {
            return;
        }
        Set<Long> questionIdSet = Sets.newHashSet();
        for (ContentVO contentVOTemp : contentVOList) {
            //问答
            if (TypeEnum.ANSWER.getCode().equals(contentVOTemp.getType())) {
                questionIdSet.add(contentVOTemp.getBizId());
            }
        }
        if (CollectionUtils.isNotEmpty(questionIdSet)) {
            Map<Long, ContentDO> contentDOMap = contentMapper.getByKeys(questionIdSet);
            if (MapUtils.isNotEmpty(contentDOMap)) {
                Set<Long> userIdSet = contentDOMap.values().stream().map(c -> c.getBizId()).collect(Collectors.toSet());
                Map<Long, UserInfoDO> userInfoDOMap = userInfoMapper.getByKeys(userIdSet);
                ContentDO questionContentDO = null;
                ContentVO questionContentVO = null;
                UserInfoVO userInfoVO = null;
                for (ContentVO contentVOTemp : contentVOList) {
                    //问答
                    if (TypeEnum.ANSWER.getCode().equals(contentVOTemp.getType())) {
                        questionContentDO = Optional.ofNullable(contentDOMap).map(a -> a.get(contentVOTemp.getBizId())).orElse(null);
                        questionContentVO = BeanUtils.copyProperties(questionContentDO, ContentVO.class);
                        if (null != questionContentVO) {
                            userInfoVO = UserUtils.getUserInfoVO(userInfoDOMap, questionContentVO.getUserId());
                            questionContentVO.setUserInfo(userInfoVO);
                        }
                        contentVOTemp.setQuestionContent(questionContentVO);
                    }
                }
            }
        }
    }

    /**
     * 根据id查询并且填充用户信息
     *
     * @param idSet
     * @return
     */
    public Map<Long, ContentVO> getByKeys(Set<Long> idSet) {
        if (CollectionUtils.isEmpty(idSet)) {
            return null;
        }
        Map<Long, ContentDO> contentDOMap = contentMapper.getByKeys(idSet);
        if (MapUtils.isEmpty(contentDOMap)) {
            return null;
        }
        //填充用户
        Set<Long> userIdSet = contentDOMap.values().stream().map(c -> c.getUserId()).
                collect(Collectors.toSet());
        Map<Long, UserInfoVO> userInfoVOMap = userInfoManager.getUserByIdIfNotExistReturnDefault(userIdSet);
        Map<Long, ContentVO> contentVOMap = Maps.newLinkedHashMap();
        if (MapUtils.isNotEmpty(userInfoVOMap)) {
            for (Map.Entry<Long, ContentDO> contentDOEntry : contentDOMap.entrySet()) {
                ContentVO contentVOTemp = BeanUtils.copyProperties(contentDOEntry.getValue(), ContentVO.class);
                contentVOTemp.setUserInfo(userInfoVOMap.get(contentDOEntry.getKey()));
                contentVOMap.put(contentDOEntry.getKey(), contentVOTemp);
            }
        }
        return contentVOMap;
    }

    /**
     * 填充用户信息
     *
     * @param contentVOList
     */
    public void fillUserInfo(List<ContentVO> contentVOList) {
        if (CollectionUtils.isEmpty(contentVOList)) {
            return;
        }
        Set<Long> userIdSet = contentVOList.stream().map(contentVO -> contentVO.getUserId()).collect(Collectors.toSet());
        Map<Long, UserInfoVO> userInfoVOMap = userInfoManager.getUserByIdIfNotExistReturnDefault(userIdSet);
        contentVOList.forEach(contentVO -> {
            contentVO.setUserInfo(userInfoVOMap.get(contentVO.getUserId()));
        });
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
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

    /**
     * 帖子详情
     *
     * @param contentRequest
     * @return
     */
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

    public static void main(String[] args) {
        Map<Long, ContentDO> contentDOMap = Maps.newLinkedHashMap();
        contentDOMap.put(12l, null);
        contentDOMap.values();
//        Sets.newHashSet().add(null);
        Optional.ofNullable(contentDOMap).map(a -> a.values()).map(b -> b.stream().map(c -> c.getUserId()).collect(Collectors.toSet()));
//        Optional.ofNullable(contentDOMap).map(a -> a.values().stream().map(b->b.getUserId()).collect(Collectors.toSet()));
//        Set<Long> userIdSet = Optional.ofNullable(contentDOMap).map(a -> a.values().stream().map())
    }


}
