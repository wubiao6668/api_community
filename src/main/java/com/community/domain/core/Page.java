package com.community.domain.core;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * 分页查询结果
 *
 * @param <M> 实体对象类型
 */
public class Page<M> implements Serializable {

    private Boolean hasMore;
    // 分页
    private Optional<Pagination> pagination;
    // 结果列表
    private Optional<List<M>> data;

    public Page() {
    }

    public Page(Optional<Pagination> pagination, Optional<List<M>> data) {
        this.pagination = pagination;
        this.data = data;
    }

    public Optional<Pagination> getPagination() {
        return pagination;
    }

    public void setPagination(Optional<Pagination> pagination) {
        this.pagination = pagination;
    }

    public Optional<List<M>> getData() {
        return data;
    }

    public void setData(Optional<List<M>> data) {
        this.data = data;
    }

    public Boolean getHasMore() {
        return hasMore;
    }

    public void setHasMore(Boolean hasMore) {
        this.hasMore = hasMore;
    }

//    public Optional<List<M>> getDataOptional() {
//        return Optional.ofNullable(data);
//    }

    //    /**
//     * 是否还有下一页数据
//     *
//     * @return 下一页数据标示
//     */
//    public boolean hasNext() {
//        if (data == null || data.size() < pagination.getPageSize()) {
//            return false;
//        }
//        return pagination.getPageNumber() < pagination.getTotalPage();
//    }

}