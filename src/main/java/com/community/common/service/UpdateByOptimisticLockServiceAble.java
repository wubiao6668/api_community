package com.community.common.service;

import com.community.domain.core.Response;

public interface UpdateByOptimisticLockServiceAble<M> {
    Response updateByOptimisticLock(M m);
}
