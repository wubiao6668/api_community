/**
 * Created by wubiao on - 2018/04/29.
 */

package com.community.service;

import com.community.common.service.CurdServiceAble;
import com.community.domain.model.db.LikeDO;
import com.community.domain.request.LikeRequest;
import com.community.domain.response.LikeResponse;

public interface LikeService extends CurdServiceAble<Long, LikeRequest, LikeDO, LikeResponse> {

}
