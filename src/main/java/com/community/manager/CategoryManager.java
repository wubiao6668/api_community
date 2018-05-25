/**
 * Created by wubiao on - 2018/05/25.
 */

package com.community.manager;

import com.community.dao.mapper.CategoryMapper;
import com.community.domain.core.Page;
import com.community.domain.model.db.CategoryDO;
import com.community.domain.request.CategoryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Service
public class CategoryManager {

    @Autowired
    private CategoryMapper categoryMapper;

    public Future<Page<CategoryDO>> listPageAsync(CategoryRequest request) {
        return CompletableFuture.supplyAsync(() -> categoryMapper.listPage(request));
    }
}
