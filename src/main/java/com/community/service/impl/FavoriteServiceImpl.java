/**
 * Created by wubiao on - 2018/04/29.
 */

package com.community.service.impl;

import com.community.common.service.AbstractCurdService;
import com.community.dao.mapper.FavoriteMapper;
import com.community.domain.model.db.FavoriteDO;
import com.community.domain.request.FavoriteRequest;
import com.community.domain.response.FavoriteResponse;
import com.community.service.FavoriteService;
import org.springframework.stereotype.Service;


@Service
public class FavoriteServiceImpl extends AbstractCurdService<Long, FavoriteRequest, FavoriteDO, FavoriteResponse, FavoriteMapper>
        implements FavoriteService {

}
