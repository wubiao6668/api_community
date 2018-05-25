/**
 * Created by wubiao on - 2018/05/25.
 */

package com.community.service;

import com.community.common.service.CurdServiceAble;
import com.community.domain.model.db.CategoryDO;
import com.community.domain.request.CategoryRequest;
import com.community.domain.response.CategoryResponse;

public interface CategoryService extends CurdServiceAble<Long, CategoryRequest, CategoryDO, CategoryResponse> {

}
