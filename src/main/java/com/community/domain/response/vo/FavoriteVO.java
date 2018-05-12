package com.community.domain.response.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class FavoriteVO implements Serializable {
    private static final long serialVersionUID = 6786687230772208370L;
    /**
     * id
     */
    private Long id;
    /**
     * 收藏人
     */
    private Long userId;
    /**
     * status
     */
    private Integer status;
    /**
     * 类型（1-帖子、2-回答）
     */
    private Integer type;
    /**
     * 帖子id或者问题id
     */
    private Long bizId;
    /**
     * 是否删除（0-未删除、1-已删除）
     */
    private Integer isDelete;

    private ContentVO contentVO;


}
