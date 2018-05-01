/**
 * Created by wubiao on - 2018/04/29.
 */

package com.community.manager;

import com.community.dao.mapper.FavoriteMapper;
import com.community.domain.core.Page;
import com.community.domain.model.db.FavoriteDO;
import com.community.domain.request.FavoriteRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Service
public class FavoriteManager {

    @Autowired
    private FavoriteMapper favoriteMapper;

    public Future<Page<FavoriteDO>> listPageAsync(FavoriteRequest request) {
        return CompletableFuture.supplyAsync(() -> favoriteMapper.listPage(request));
    }
}
