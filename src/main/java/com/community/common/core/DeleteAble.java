package com.community.common.core;

import org.apache.ibatis.annotations.Param;

public interface DeleteAble<KEY> {
    int delete(@Param("keys") KEY... keys);
}
