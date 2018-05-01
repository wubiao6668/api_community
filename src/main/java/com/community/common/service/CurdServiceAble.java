package com.community.common.service;

import com.community.domain.core.PageAble;

public interface CurdServiceAble<KEY, Q extends PageAble, M, T> extends DeleteServiceAble<KEY>, DeleteServicePhysicalAble<KEY>, ListServiceAble<Q, T>, GetByKeyServiceAble<KEY>, GetByKeysServiceAble<KEY>, InsertServiceAble<M>, InsertSelectiveServiceAble<M>, UpdateServiceAble<M>, UpdateByOptimisticLockServiceAble<M>, UpdateSelectiveServiceAble<M> {

}
