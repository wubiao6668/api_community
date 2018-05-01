package com.community.common.core;

import com.community.domain.core.Page;
import com.community.domain.core.PageAble;

public interface ListAble<Q extends PageAble, M> {


    Page<M> listPage(Q q);

    int listPageCount(Q q);
}
