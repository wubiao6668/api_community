package com.community.domain.response.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserInfoVO implements Serializable{
    private static final long serialVersionUID = -7322807687954594631L;

    /**
     * id
     */
    private Long id;
    /**
     * 名称
     */
    private String nickName;
    /**
     * 头像
     */
    private String avatar;
}
