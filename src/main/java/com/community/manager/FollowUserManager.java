/**
 * Created by wubiao on - 2018/04/29.
 */

package com.community.manager;

import com.community.common.constant.Constant.FollowStatusEnum;
import com.community.common.util.BeanUtils;
import com.community.common.util.UserUtils;
import com.community.dao.mapper.FollowUserMapper;
import com.community.dao.mapper.UserInfoMapper;
import com.community.domain.core.Page;
import com.community.domain.core.Pagination;
import com.community.domain.core.Response;
import com.community.domain.model.db.FollowUserDO;
import com.community.domain.model.db.UserInfoDO;
import com.community.domain.request.FollowUserRequest;
import com.community.domain.response.FollowUserResponse;
import com.community.domain.response.vo.FollowUserVO;
import com.community.domain.response.vo.UserInfoVO;
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
import java.util.stream.Collectors;

@Service
public class FollowUserManager {

    @Autowired
    private FollowUserMapper followUserMapper;
    @Autowired
    private UserInfoManager userInfoManager;
    @Autowired
    private UserInfoMapper userInfoMapper;

    public Response<FollowUserResponse> listPage(FollowUserRequest request) {
        if (null == request) {
            request = new FollowUserRequest();
        }
        //查找我关注的
        Page<FollowUserDO> followUserDOPage = followUserMapper.listPage(request);
        List<FollowUserDO> followUserDOList = Optional.ofNullable(followUserDOPage).flatMap(Page::getData).orElse(null);
        Pagination pagination = Optional.ofNullable(followUserDOPage).flatMap(Page::getPagination).orElse(null);
        Boolean hasMore = Optional.ofNullable(followUserDOPage).map(Page::getHasMore).orElse(null);
        List<FollowUserVO> followUserVOList = null;
        if (CollectionUtils.isNotEmpty(followUserDOList)) {
            followUserVOList = BeanUtils.list2list(followUserDOList, FollowUserVO.class);
            //补充用户信息
            Set<Long> userIdSet = followUserVOList.stream().map(a -> a.getFollowUserId()).collect(Collectors.toSet());
            Map<Long, UserInfoDO> userInfoDOMap = userInfoMapper.getByKeys(userIdSet);
            followUserVOList.forEach(followUserVO -> {
                UserInfoVO userInfoVO = UserUtils.getUserInfoVO(userInfoDOMap, followUserVO.getUserId());
                followUserVO.setFollowUserInfo(userInfoVO);
            });
            //
            //查询是否我关注的人是否关注我
            if (request.isQueryFollowEachOther()) {
                FollowUserRequest followMeRequest = new FollowUserRequest();
                followMeRequest.setUserIdSet(userIdSet);
                followMeRequest.setFollowUserId(LoginContext.getUserId());
                followMeRequest.setStatus(FollowStatusEnum.FOLLOW.getCode());
                followMeRequest.getPagination().setPageSize(userIdSet.size());
                Page<FollowUserDO> followMeUserDOPage = followUserMapper.listPage(followMeRequest);
                Set<Long> followMeUserIdSet = Optional.ofNullable(followMeUserDOPage).flatMap(Page::getData).
                        map(a -> a.stream()).map(b -> b.map(followUserDO -> followUserDO.getUserId()).
                        collect(Collectors.toSet())).orElse(Sets.newHashSet());
                //设置是否相互关注
                followUserVOList.forEach(followUserVO -> followUserVO.setFollowEachOther(
                        followMeUserIdSet.contains(followUserVO.getFollowUserId())
                ));
            }
        }
        FollowUserResponse followUserResponse = new FollowUserResponse();
        followUserResponse.setFollowUserList(followUserVOList);
        followUserResponse.setPagination(pagination);
        followUserResponse.setHasMore(hasMore);
        return Response.success(followUserResponse);
    }


    public Future<Response<FollowUserResponse>> listPageAsync(FollowUserRequest request) {
        return CompletableFuture.supplyAsync(() -> this.listPage(request));
    }
}
