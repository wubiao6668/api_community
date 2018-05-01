/**
 * Created by wubiao on - 2018/04/29.
 */

package com.community.dao.mapper;


import com.community.common.core.CurdAble;
import com.community.domain.model.db.CommentDO;
import com.community.domain.request.CommentRequest;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper extends CurdAble<Long, CommentRequest, CommentDO> {

}
