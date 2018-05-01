package com.community.common.core;

public interface UpdateByOptimisticLockAble<M> {
    int updateByOptimisticLock(M m);
}
