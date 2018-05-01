package com.community.common.service;

import com.community.domain.core.Response;

public interface InsertServiceAble<M> {
    Response insert(M m);
}
