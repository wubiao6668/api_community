package com.community.common.service;

import com.community.domain.core.Response;

public interface GetByKeyServiceAble<KEY> {
    Response getByKey(KEY key);
}
