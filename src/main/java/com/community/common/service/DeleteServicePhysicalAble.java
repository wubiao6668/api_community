package com.community.common.service;

import com.community.domain.core.Response;

public interface DeleteServicePhysicalAble<KEY> {
    Response deletePhysical(KEY key);
}
