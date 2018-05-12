/**
 * Created by wubiao on - 2018/04/29.
 */

package com.community.manager;

import com.community.common.util.BeanUtils;
import com.community.dao.mapper.FavoriteMapper;
import com.community.domain.core.Page;
import com.community.domain.core.Pagination;
import com.community.domain.core.Response;
import com.community.domain.model.db.FavoriteDO;
import com.community.domain.request.FavoriteRequest;
import com.community.domain.response.FavoriteResponse;
import com.community.domain.response.vo.ContentVO;
import com.community.domain.response.vo.FavoriteVO;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import static com.community.common.constant.Constant.TypeEnum;

@Service
public class FavoriteManager {

    @Autowired
    private FavoriteMapper favoriteMapper;
    @Autowired
    private ContentManager contentManager;

    /**
     * 查找我的收藏
     *
     * @param request
     * @return
     */
    public Response<FavoriteResponse> listPage(FavoriteRequest request) {
        if (null == request) {
            request = new FavoriteRequest();
        }
        Page<FavoriteDO> favoriteDOPage = favoriteMapper.listPage(request);
        List<FavoriteDO> favoriteDOList = Optional.ofNullable(favoriteDOPage).flatMap(Page::getData).orElse(null);
        Pagination pagination = Optional.ofNullable(favoriteDOPage).flatMap(Page::getPagination).orElse(null);
        Boolean hasMore = Optional.ofNullable(favoriteDOPage).map(Page::getHasMore).orElse(null);
        List<FavoriteVO> favoriteVOList = null;
        if (CollectionUtils.isNotEmpty(favoriteDOList)) {
            favoriteVOList = BeanUtils.list2list(favoriteDOList, FavoriteVO.class);
            this.fillContent(favoriteVOList);
        }
        //返回结果
        FavoriteResponse favoriteResponse = new FavoriteResponse();
        favoriteResponse.setFavoriteList(favoriteVOList);
        favoriteResponse.setPagination(pagination);
        favoriteResponse.setHasMore(hasMore);
        return Response.success(favoriteResponse);
    }

    public void fillContent(List<FavoriteVO> favoriteVOList) {
        if (CollectionUtils.isEmpty(favoriteVOList)) {
            return;
        }
        //查找收藏的
        Set<Long> contentId = favoriteVOList.stream().
                filter(favoriteVO -> TypeEnum.isContentType(favoriteVO.getType())).
                map(favoriteVO -> favoriteVO.getBizId()).collect(Collectors.toSet());

        Map<Long, ContentVO> contentVOMap = contentManager.getByKeys(contentId);
        if (MapUtils.isEmpty(contentVOMap)) {
            return;
        }
        //如果是问答需要填充问题
        List<ContentVO> answerContentVOList = Lists.newArrayList();
        favoriteVOList.forEach(favoriteVO -> {
            ContentVO contentVO = contentVOMap.get(favoriteVO.getBizId());
            favoriteVO.setContentVO(contentVO);
            if (TypeEnum.ANSWER.getCode().equals(contentVO.getType())) {
                answerContentVOList.add(contentVO);
            }
        });
        if (CollectionUtils.isEmpty(answerContentVOList)) {
            return;
        }
        //填充问题
        contentManager.fillQuestionContentFromAnswer(answerContentVOList);
    }

    public Future<Page<FavoriteDO>> listPageAsync(FavoriteRequest request) {
        return CompletableFuture.supplyAsync(() -> favoriteMapper.listPage(request));
    }



}
