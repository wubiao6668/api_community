package com.community.domain.core;

import java.io.Serializable;

/**
 * 查询接口
 */
public interface PageAble extends Serializable {

    PaginationAble getPagination();

    void setPagination(PaginationAble pagination);

}