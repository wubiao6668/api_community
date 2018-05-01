package com.community.common.core;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface GetByKeysAble<KEY, M> {
    @MapKey(value = "id")
    Map<KEY, M> getByKeys(@Param("keys") KEY... keys);
}
