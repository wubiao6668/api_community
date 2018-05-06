/**
 * Created by wubiao on - 2018/04/29.
 */

package com.community.manager;

import com.community.common.enums.ApiHttpStatus;
import com.community.common.util.BeanUtils;
import com.community.common.util.FutureUtils;
import com.community.dao.mapper.CommentMapper;
import com.community.dao.mapper.UserInfoMapper;
import com.community.domain.core.Page;
import com.community.domain.core.PaginationAble;
import com.community.domain.core.Response;
import com.community.domain.model.db.CommentDO;
import com.community.domain.model.db.LikeDO;
import com.community.domain.model.db.OrganizationMemberDO;
import com.community.domain.model.db.UserInfoDO;
import com.community.domain.request.CommentRequest;
import com.community.domain.request.LikeRequest;
import com.community.domain.request.OrganizationMemberRequest;
import com.community.domain.response.CommentResponse;
import com.community.domain.response.OrganizationMemberResponse;
import com.community.domain.response.UserInfoResponse;
import com.community.domain.response.vo.CommentVO;
import com.community.domain.response.vo.UserInfoVO;
import com.community.domain.session.LoginContext;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
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
import static java.util.stream.Collectors.toSet;

@Service
public class CommentManager {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private UserInfoManager userInfoManager;
    @Autowired
    private LikeManager likeManager;
    @Autowired
    private OrganizationMemberManager organizationMemberManager;
    @Autowired
    private UserInfoMapper userInfoMapper;

    public Future<Page<CommentDO>> listPageAsync(CommentRequest request) {
        return CompletableFuture.supplyAsync(() -> commentMapper.listPage(request));
    }

    public Response<Page<CommentResponse>> listPageByBizId(CommentRequest request) {
        if (null != request.getBizId()) {
            return Response.fail(ApiHttpStatus.ARGUMENT_ERROR.getCode(), "id 不能为空");
        }
        if (null != request.getType()) {
            return Response.fail(ApiHttpStatus.ARGUMENT_ERROR.getCode(), "type 不能为空");
        }
        Long bizId = request.getBizId();
        Integer type = request.getType();
        Page<CommentDO> commentDOPage = commentMapper.listPage(request);
        List<CommentDO> commentDOList = Optional.ofNullable(commentDOPage).flatMap(Page::getData).orElse(null);
        List<CommentResponse> commentResponseList = null;
        if (CollectionUtils.isNotEmpty(commentDOList)) {
            commentResponseList = BeanUtils.list2list(commentDOList, CommentResponse.class);
            //用户id
            Set<Long> userIdSet = Sets.newHashSet();
            Set<Long> idSet = Sets.newHashSet();
            commentResponseList.forEach(commentResponse -> {
                userIdSet.add(commentResponse.getUserId());
                idSet.add(commentResponse.getId());
            });
            //评论用户
            Future<Map<Long, UserInfoDO>> userInfoMapFuture = userInfoManager.getUserInfoByIdsAsync(userIdSet);
            //当前用户是否点赞
            LikeRequest likeRequest = new LikeRequest();
            likeRequest.setUserIdSet(idSet);
            likeRequest.setBizId(bizId);
            likeRequest.setStatus(LikeEnum.LIKE.getCode());
            Future<Map<Long, LikeDO>> likeMapFuture = likeManager.getByBizIdUserIdAndTypeAsync(likeRequest);
            //如果是活动或者圈子需要查询当前用户身份
            Long loginUserId = LoginContext.getUserId();
            OrganizationMemberDO organizationMemberDO = null;
            if (TypeEnum.ORG.getCode().equals(type) || TypeEnum.ACTIVITY.getCode().equals(type)) {
                //当前用户身份
                OrganizationMemberRequest organizationMemberRequest = new OrganizationMemberRequest();
                organizationMemberRequest.setUserId(loginUserId);
                organizationMemberRequest.setOrgId(bizId);
                Future<OrganizationMemberDO> memberDOFuture = organizationMemberManager.getOrgMemberAsync(organizationMemberRequest);
                organizationMemberDO = FutureUtils.get(memberDOFuture);
            }
            final Map<Long, UserInfoDO> userInfoDOMap = FutureUtils.get(userInfoMapFuture);
            final Map<Long, LikeDO> likeDOMap = FutureUtils.get(likeMapFuture);
            //圈子角色
            OrganizationMemberResponse organizationMemberResponse = BeanUtils.copyProperties(organizationMemberDO, OrganizationMemberResponse.class);
            commentResponseList.forEach(commentResponse -> {
                //评论用户
                UserInfoDO userInfoDO = Optional.ofNullable(userInfoDOMap).
                        map(userFMap -> userFMap.get(commentResponse.getUserId())).
                        orElse(DEFAULT_USER_INFO);
                UserInfoResponse userInfoResponse = BeanUtils.copyProperties(userInfoDO, UserInfoResponse.class);
                commentResponse.setUserInfo(userInfoResponse);
                //设置是否点赞
                Boolean isLike = Optional.ofNullable(likeDOMap).map(likeFMap -> likeFMap.get(commentResponse.getId())).
                        map(likeDO -> LikeEnum.LIKE.getCode().equals(likeDO.getStatus())).
                        orElse(null);
                commentResponse.setIsLike(isLike);
                //圈子角色
                commentResponse.setOrganizationMember(organizationMemberResponse);
                //是否自己评论
                commentResponse.setIsOwn(commentResponse.getUserId().equals(loginUserId));
            });
        }
        Page<CommentResponse> responsePage = new Page<>();
        responsePage.setData(Optional.ofNullable(commentResponseList));
        responsePage.setPagination(Optional.ofNullable(commentDOPage).flatMap(page -> page.getPagination()));
        return Response.success(responsePage);
    }

    public Response<CommentResponse> detail(CommentRequest request) {
        if (null == request || null == request.getId()) {
            return Response.fail(ApiHttpStatus.ARGUMENT_ERROR.getCode(), "id 不能为空");
        }
        Long id = request.getId();
        CommentDO commentDO = commentMapper.getByKey(id);
        if (null == commentDO || IsDeleteEnum.YES.getCode().equals(commentDO.getIsDelete())) {
            return Response.fail(ApiHttpStatus.NOT_FOUND.getCode(), "评论不存在");
        }
        CommentVO commentVO = BeanUtils.copyProperties(commentDO, CommentVO.class);
        //当前用户id
        Long loginUserId = LoginContext.getUserId();
        Set<Long> userIdSet = Sets.newLinkedHashSet();
        Future<Map<Long, LikeDO>> likeDOMapFuture = null;
        if (null != loginUserId) {
            //查询自己是否点赞了改评论
            LikeRequest likeRequest = new LikeRequest();
            likeRequest.setBizId(commentVO.getId());
            likeRequest.setType(commentVO.getType());
            likeRequest.setUserId(loginUserId);
            likeRequest.setStatus(LikeEnum.LIKE.getCode());
            likeDOMapFuture = likeManager.getByBizIdUserIdAndTypeAsync(likeRequest);
        }
        //查询点赞人
        LikeRequest likeListRequest = new LikeRequest();
        likeListRequest.setBizId(commentVO.getId());
        likeListRequest.setType(commentVO.getType());
        likeListRequest.setIsDelete(IsDeleteEnum.NO.getCode());
        PaginationAble paginationAble = likeListRequest.getPagination();
        if (null == paginationAble) {
            paginationAble = new PaginationAble();
        }
        paginationAble.setPageSize(DEFAULT_NUM);
        likeListRequest.setPagination(paginationAble);
        Future<Page<LikeDO>> commentDOPageFuture = likeManager.listPageAsync(likeListRequest);
        //取结果
        Map<Long, LikeDO> likeDOMap = FutureUtils.get(likeDOMapFuture);
        Page<LikeDO> likeDOPage = FutureUtils.get(commentDOPageFuture);
        //当前用户是否点赞
        Boolean isLoginUserLike = Optional.ofNullable(likeDOMap).map(a -> a.get(loginUserId)).
                map(likeDO -> LikeEnum.LIKE.getCode().equals(likeDO.getStatus())).
                orElse(false);
        commentVO.setIsLike(isLoginUserLike);
        //点赞用户
        Set<Long> likeUserIdSet = Optional.ofNullable(likeDOPage).
                flatMap(Page::getData).map(a -> a.stream()).
                map(b -> b.map(likeDO -> likeDO.getUserId()).collect(toSet())).orElse(Sets.newLinkedHashSet());
        userIdSet.addAll(likeUserIdSet);
        //查询用户
        Map<Long, UserInfoDO> userInfoDOMap = userInfoMapper.getByKeys(userIdSet);
        List<UserInfoVO> likeUserInfoVOList = Lists.newArrayList();
        likeUserIdSet.forEach(aLong -> {
            UserInfoDO userInfoDO = Optional.ofNullable(userInfoDOMap).map(a -> a.get(aLong)).orElse(DEFAULT_USER_INFO);
            likeUserInfoVOList.add(BeanUtils.copyProperties(userInfoDO, UserInfoVO.class));
        });
        commentVO.setLikeUserInfo(likeUserInfoVOList);
        //返回对象
        CommentResponse commentResponse = new CommentResponse();
        commentResponse.setComment(commentVO);
        return Response.success(commentResponse);
    }

    public static void main(String[] args) {
        Map<Long, UserInfoDO> userInfoDOMap = Maps.newLinkedHashMap();
        Long userId = null;
        UserInfoDO userInfoDO = Optional.ofNullable(userInfoDOMap).map(userFMap -> userFMap.get(userId)).orElse(DEFAULT_USER_INFO);
        System.out.println(userInfoDO);
    }
}
