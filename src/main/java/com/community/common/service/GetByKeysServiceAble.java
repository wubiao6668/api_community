package com.community.common.service;

import com.community.domain.core.Response;

import java.util.Set;

public interface GetByKeysServiceAble<KEY> {
    Response getByKeys(Set<KEY> keys);
}
