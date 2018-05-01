package com.community.domain.core;

import lombok.Data;

import java.io.Serializable;

/**
 * 分页查询条件
 */
@Data
public class PaginationAble implements Serializable {

    private static final long serialVersionUID = -2381497874862313739L;
    private Integer page;
    private Integer startRow;
    private Integer pageSize;

    private Integer limitPageSize;

    private Integer pageType;

    public int getPage() {
        if (null == page || page <= 0) {
            return 1;
        }
        return page;
    }

    public Integer getPageSize() {
        if (null == pageSize || pageSize <= 0) {
            return 20;
        }
        return pageSize;
    }

    public Integer getStartRow() {
        if (null == startRow) {
            return (getPage() - 1) * getPageSize();
        }
        return startRow;
    }

    public Integer getLimitPageSize() {
        if (null == limitPageSize) {
            return getPageSize() * 10;
        }
        return limitPageSize;
    }
}