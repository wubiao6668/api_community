/**
 * Created by wubiao on - 2018/04/29.
 */

package com.community.dao.mapper;


import com.community.common.core.CurdAble;
import com.community.domain.model.db.ContentDO;
import com.community.domain.request.ContentRequest;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ContentMapper extends CurdAble<Long, ContentRequest, ContentDO> {

}
