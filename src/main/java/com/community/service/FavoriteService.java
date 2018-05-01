/**
 * Created by wubiao on - 2018/04/29.
 */

package com.community.service;

import com.community.common.service.CurdServiceAble;
import com.community.domain.model.db.FavoriteDO;
import com.community.domain.request.FavoriteRequest;
import com.community.domain.response.FavoriteResponse;

public interface FavoriteService extends CurdServiceAble<Long, FavoriteRequest, FavoriteDO, FavoriteResponse> {

}
