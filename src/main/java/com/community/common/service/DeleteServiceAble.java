package com.community.common.service;

import com.community.domain.core.Response;
import org.apache.ibatis.annotations.Param;

public interface DeleteServiceAble<KEY> {
    Response delete(@Param("keys") KEY... keys);
}
