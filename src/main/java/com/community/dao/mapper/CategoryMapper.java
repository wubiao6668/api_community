/**
 * Created by wubiao on - 2018/05/25.
 */

package com.community.dao.mapper;


import com.community.common.core.CurdAble;
import com.community.domain.model.db.CategoryDO;
import com.community.domain.request.CategoryRequest;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper extends CurdAble<Long, CategoryRequest, CategoryDO> {

}
