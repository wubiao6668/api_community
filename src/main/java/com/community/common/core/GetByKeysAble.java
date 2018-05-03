package com.community.common.core;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.Map;
import java.util.Set;

public interface GetByKeysAble<KEY, M> {
    @MapKey(value = "id")
    Map<KEY, M> getByKeys(@Param("keys") Set<KEY> keys);
}
