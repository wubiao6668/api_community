/**
 * Created by wubiao on - 2018/04/29.
 */

package com.community.manager;

import com.community.common.constant.Constant;
import com.community.common.util.BeanUtils;
import com.community.dao.mapper.FollowInfoMapper;
import com.community.domain.core.Page;
import com.community.domain.core.Pagination;
import com.community.domain.core.Response;
import com.community.domain.model.db.FollowInfoDO;
import com.community.domain.request.FollowInfoRequest;
import com.community.domain.response.FollowInfoResponse;
import com.community.domain.response.vo.ContentVO;
import com.community.domain.response.vo.FollowInfoVO;
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

@Service
public class FollowInfoManager {

    @Autowired
    private FollowInfoMapper followInfoMapper;
    @Autowired
    private ContentManager contentManager;

    public Response<FollowInfoResponse> listPage(FollowInfoRequest request) {
        if (null == request) {
            request = new FollowInfoRequest();
        }
        Page<FollowInfoDO> followInfoDOPage = followInfoMapper.listPage(request);
        List<FollowInfoDO> followInfoDOList = Optional.ofNullable(followInfoDOPage).flatMap(Page::getData).orElse(null);
        Pagination pagination = Optional.ofNullable(followInfoDOPage).flatMap(Page::getPagination).orElse(null);
        Boolean hasMore = Optional.ofNullable(followInfoDOPage).map(Page::getHasMore).orElse(null);
        List<FollowInfoVO> followInfoVOList = null;
        if (CollectionUtils.isNotEmpty(followInfoDOList)) {
            followInfoVOList = BeanUtils.list2list(followInfoDOList, FollowInfoVO.class);
            //填充问题
            if (request.isQueryQuestion()) {
                this.fillQuestion(followInfoVOList);
            }
        }
        //返回结果
        FollowInfoResponse followInfoResponse = new FollowInfoResponse();
        followInfoResponse.setHasMore(hasMore);
        followInfoResponse.setPagination(pagination);
        followInfoResponse.setFollowInfoList(followInfoVOList);
        return Response.success(followInfoResponse);
    }

    public void fillQuestion(List<FollowInfoVO> followInfoVOList) {
        if (CollectionUtils.isEmpty(followInfoVOList)) {
            return;
        }
        //问题id
        Set<Long> questionIdSet = followInfoVOList.stream().
                filter(followInfoVO -> Constant.TypeEnum.ASK.getCode().equals(followInfoVO.getType())).
                map(a -> a.getBizId()).collect(Collectors.toSet());
        if (CollectionUtils.isEmpty(questionIdSet)) {
            return;
        }
        Map<Long, ContentVO> contentVOMap = contentManager.getByKeys(questionIdSet);
        if (MapUtils.isEmpty(contentVOMap)) {
            return;
        }
        followInfoVOList.forEach(followInfoVO -> {
            if (Constant.TypeEnum.ASK.getCode().equals(followInfoVO.getType())) {
                followInfoVO.setQuestionContent(contentVOMap.get(followInfoVO.getBizId()));
            }
        });

    }

    public Future<Page<FollowInfoDO>> listPageAsync(FollowInfoRequest request) {
        return CompletableFuture.supplyAsync(() -> followInfoMapper.listPage(request));
    }

    public Map<Long, FollowInfoDO> getByUserIdBizIdAndType(FollowInfoRequest request) {
        if (null == request.getBizId() || null == request.getType()
                || (null == request.getUserId() && CollectionUtils.isEmpty(request.getUserIdSet()))) {
            return null;
        }
        return followInfoMapper.getByUserIdBizIdAndType(request);
    }

    public Future<Map<Long, FollowInfoDO>> getByUserIdBizIdAndTypeAsync(FollowInfoRequest request) {
        return CompletableFuture.supplyAsync(() -> this.getByUserIdBizIdAndType(request));
    }
}
