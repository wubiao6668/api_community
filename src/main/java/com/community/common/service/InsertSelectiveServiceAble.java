package com.community.common.service;

import com.community.domain.core.Response;

public interface InsertSelectiveServiceAble<M> {
    Response insertSelective(M m);
}
