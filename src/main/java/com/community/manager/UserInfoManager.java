/**
 * Created by wubiao on - 2018/05/01.
 */

package com.community.manager;

import com.community.common.constant.Constant;
import com.community.common.util.BeanUtils;
import com.community.dao.mapper.UserInfoMapper;
import com.community.domain.core.Page;
import com.community.domain.model.db.UserInfoDO;
import com.community.domain.request.UserInfoRequest;
import com.community.domain.response.vo.UserInfoVO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Service
public class UserInfoManager {

    @Autowired
    private UserInfoMapper userInfoMapper;

    public Future<Page<UserInfoDO>> listPageAsync(UserInfoRequest request) {
        return CompletableFuture.supplyAsync(() -> userInfoMapper.listPage(request));
    }

    Future<Map<Long, UserInfoDO>> getUserInfoByIdsAsync(Set<Long> userIdSets) {
        if (CollectionUtils.isEmpty(userIdSets)) {
            return null;
        }
        return CompletableFuture.supplyAsync(() -> userInfoMapper.getByKeys(userIdSets));
    }

    public UserInfoVO getUserByIdIfNotExistReturnDefault(Long userId) {
        UserInfoDO userInfoDO = null;
        if (null != userId) {
            userInfoDO = userInfoMapper.getByKey(userId);
        }
        if (null == userInfoDO) {
            userInfoDO = Constant.DEFAULT_USER_INFO;
        }
        return BeanUtils.copyProperties(userInfoDO, UserInfoVO.class);
    }

}
