/**
 * Created by wubiao on - 2018/04/29.
 */

package com.community.domain.response.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class TagVO implements Serializable {
    private static final long serialVersionUID = 5454155825314635342L;

    /**
     * id
     */
    private Long id;
    /**
     * 标签名称
     */
    private String name;
    /**
     * 业务主表id
     */
    private Long bizId;
    /**
     * 标签类型(1-帖子、2-活动)
     */
    private Integer type;
    /**
     * 顺序
     */
    private Integer sequence;
    /**
     * 0-展示1-隐藏
     */
    private Integer isShow;

    @Override
    public String toString() {
        return super.toString();
    }
}

