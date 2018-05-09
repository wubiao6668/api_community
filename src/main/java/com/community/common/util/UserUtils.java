package com.community.common.util;

import com.community.domain.model.db.UserInfoDO;
import com.community.domain.response.vo.UserInfoVO;

import java.util.Map;
import java.util.Optional;

import static com.community.common.constant.Constant.DEFAULT_USER_INFO;

public class UserUtils {

    /**
     * 从map获取用户信息 如果不存在 则取默认的
     *
     * @param userInfoDOMap
     * @param userId
     * @return
     */
    public static UserInfoDO getUserInfo(Map<Long, UserInfoDO> userInfoDOMap, Long userId) {
        return Optional.ofNullable(userInfoDOMap).map(a -> a.get(userId)).orElse(DEFAULT_USER_INFO);
    }

    public static UserInfoVO getUserInfoVO(Map<Long, UserInfoDO> userInfoDOMap, Long userId) {
        UserInfoDO userInfoDO = getUserInfo(userInfoDOMap, userId);
        return BeanUtils.copyProperties(userInfoDO, UserInfoVO.class);
    }
}
