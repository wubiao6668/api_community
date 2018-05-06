/**
 * Created by wubiao on - 2018/04/29.
 */

package com.community.manager;

import com.community.common.util.BeanUtils;
import com.community.dao.mapper.ReplyMapper;
import com.community.dao.mapper.UserInfoMapper;
import com.community.domain.core.Page;
import com.community.domain.core.Response;
import com.community.domain.model.db.ReplyDO;
import com.community.domain.model.db.UserInfoDO;
import com.community.domain.request.ReplyRequest;
import com.community.domain.response.ReplyResponse;
import com.community.domain.response.vo.ReplyVO;
import com.community.domain.response.vo.UserInfoVO;
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

import static com.community.common.constant.Constant.DEFAULT_USER_INFO;
import static com.community.common.constant.Constant.TypeEnum;

@Service
public class ReplyManager {

    @Autowired
    private ReplyMapper replyMapper;
    @Autowired
    private UserInfoManager userInfoManager;
    @Autowired
    private UserInfoMapper userInfoMapper;

    public Future<Page<ReplyDO>> listPageAsync(ReplyRequest request) {
        return CompletableFuture.supplyAsync(() -> replyMapper.listPage(request));
    }

    public Future<Map<Long, ReplyDO>> getByKeysAsync(Set<Long> replyIds) {
        if (null == replyIds) {
            return null;
        }
        return CompletableFuture.supplyAsync(() -> replyMapper.getByKeys(replyIds));
    }

    public Response<ReplyResponse> listPage(ReplyRequest request) {
        Page<ReplyDO> replyDOPage = replyMapper.listPage(request);
        List<ReplyDO> replyDOList = Optional.ofNullable(replyDOPage).flatMap(Page::getData).orElse(null);
        ReplyResponse replyResponse = new ReplyResponse();
        if (CollectionUtils.isNotEmpty(replyDOList)) {
            //用户id
            Set<Long> userIdSet = Sets.newHashSet();
            //回复id
            Set<Long> replyIdSet = Sets.newHashSet();
            replyDOList.forEach(replyDO -> {
                userIdSet.add(replyDO.getUserId());
                if (TypeEnum.REPLY.getCode().equals(replyDO.getType())) {
                    replyIdSet.add(replyDO.getReplyId());
                }
            });
            Map<Long, ReplyDO> replyDOMap = replyMapper.getByKeys(replyIdSet);

            Optional.ofNullable(replyDOMap).map(a -> a.values()).
                    map(b -> b.stream().map(c -> userIdSet.add(c.getUserId())));
            Map<Long, UserInfoDO> userInfoDOMap = userInfoMapper.getByKeys(userIdSet);
            List<ReplyVO> replyVOList = BeanUtils.list2list(replyDOList, ReplyVO.class);
            Optional<Map<Long, UserInfoDO>> userInfoDOMapOptional = Optional.ofNullable(userInfoDOMap);
            replyVOList.forEach(replyVO -> {
                //回复对象
                UserInfoDO userInfoDO = null;
                userInfoDO = Optional.ofNullable(userInfoDOMapOptional).flatMap(a -> a.map(b -> b.get(replyVO.getUserId()))).orElse(DEFAULT_USER_INFO);
                replyVO.setUserInfo(BeanUtils.copyProperties(userInfoDO, UserInfoVO.class));
                //设置被回复主体
                if (TypeEnum.REPLY.getCode().equals(replyVO.getType())) {
                    ReplyDO replyDO = Optional.ofNullable(replyDOMap).map(a -> a.get(replyVO.getId())).orElse(null);
                    ReplyVO replyVOTemp = BeanUtils.copyProperties(replyDO, ReplyVO.class);
                    userInfoDO = Optional.ofNullable(replyVOTemp).flatMap(a -> userInfoDOMapOptional.map(b -> b.get(a.getUserId()))).orElse(DEFAULT_USER_INFO);
                    replyVO.setUserInfo(BeanUtils.copyProperties(userInfoDO, UserInfoVO.class));
                    replyVO.setReplyInfo(replyVOTemp);
                }
            });
            //设置返回值
            Page returnPage = new Page();
            returnPage.setPagination(replyDOPage.getPagination());
            returnPage.setData(Optional.ofNullable(replyVOList));
            replyResponse.setPage(returnPage);
        }
        return Response.success(replyResponse);
    }
}
