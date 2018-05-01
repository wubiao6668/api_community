package com.community.domain.session;

import lombok.Data;

@Data
public class UserSession implements UserDetail {
    /**
     * 用户ID
     */
    private long userId;
    /**
     * 姓名
     */
    private String userName;


}
