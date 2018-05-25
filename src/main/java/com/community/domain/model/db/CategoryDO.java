/**
 * Created by wubiao on - 2018/05/25.
 */

package com.community.domain.model.db;

import lombok.Data;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class CategoryDO implements Serializable {
    private static final long serialVersionUID = 5454155825314635342L;
    /**
     * id
     */
    private Long id;
    /**
     * 分类名称
     */
    private String name;
    /**
     * 分类类型(1-群)
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
    /**
     * 父级节点
     */
    private Long parentId;
    /**
     * 节点数
     */
    private Long level;
    /**
     * 是否删除（0-未删除、1-已删除）
     */
    private Integer isDelete;
    /**
     * 创建者user_id
     */
    private Long createUserId;
    /**
     * createTime
     */
    private LocalDateTime createTime;
    /**
     * 修改人user_id
     */
    private Long updateUserId;
    /**
     * 修改时间
     */
    private LocalDateTime updateTime;
    /**
     * 版本号
     */
    private Integer sysVersion;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}

