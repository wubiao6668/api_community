package com.community.common.service;

import com.community.domain.core.Response;

public interface UpdateServiceAble<M> {
    Response update(M m);
}
