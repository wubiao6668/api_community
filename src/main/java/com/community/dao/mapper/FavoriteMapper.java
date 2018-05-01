/**
 * Created by wubiao on - 2018/04/29.
 */

package com.community.dao.mapper;


import com.community.common.core.CurdAble;
import com.community.domain.model.db.FavoriteDO;
import com.community.domain.request.FavoriteRequest;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FavoriteMapper extends CurdAble<Long, FavoriteRequest, FavoriteDO> {

}
