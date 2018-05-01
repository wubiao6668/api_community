package com.community.common.core;


import com.community.domain.core.PageAble;

public interface CurdAble<KEY, Q extends PageAble, M> extends DeleteAble<KEY>, DeletePhysicalAble<KEY>, ListAble<Q, M>, GetByKeyAble<KEY, M>, GetByKeysAble<KEY, M>, InsertAble<M>, InsertSelectiveAble<M>, UpdateAble<M>, UpdateByOptimisticLockAble<M>, UpdateSelectiveAble<M> {

}
