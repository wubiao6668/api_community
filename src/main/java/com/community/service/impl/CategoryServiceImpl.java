/**
 * Created by wubiao on - 2018/05/25.
 */

package com.community.service.impl;

import com.community.common.service.AbstractCurdService;
import com.community.dao.mapper.CategoryMapper;
import com.community.domain.model.db.CategoryDO;
import com.community.domain.request.CategoryRequest;
import com.community.domain.response.CategoryResponse;
import com.community.service.CategoryService;
import org.springframework.stereotype.Service;


@Service
public class CategoryServiceImpl extends AbstractCurdService<Long, CategoryRequest, CategoryDO, CategoryResponse, CategoryMapper>
        implements CategoryService {

}
