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
    private Pagination pagination;
    // 结果列表
    private List<M> data;

    public Page() {
    }

    public Page(Pagination pagination, List<M> data) {
        this.pagination = pagination;
        this.data = data;
    }

    public Pagination getPagination() {
        return this.pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public List<M> getData() {
        return this.data;
    }

    public void setData(List<M> data) {
        this.data = data;
    }

    public Boolean getHasMore() {
        return hasMore;
    }

    public void setHasMore(Boolean hasMore) {
        this.hasMore = hasMore;
    }

    public Optional<List<M>> getDataOptional() {
        return Optional.ofNullable(data);
    }

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