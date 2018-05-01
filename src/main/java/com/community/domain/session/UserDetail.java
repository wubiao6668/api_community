package com.community.domain.session;

import java.io.Serializable;

/**
 * 会话用户
 */
public interface UserDetail extends Serializable {

    /**
     * 获取当前用户ID
     *
     * @return 当前用户ID
     */
    long getUserId();

    /**
     * 获取名称
     *
     * @return 名称
     */
    String getUserName();


}
