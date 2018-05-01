package com.community.common.service;

import com.community.domain.core.PageAble;
import com.community.domain.core.Response;

public interface ListServiceAble<Q extends PageAble, T> {


    Response listPage(Q q, Class<T> tClazz);

    Response listPageCount(Q q);
}
