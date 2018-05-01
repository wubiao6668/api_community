package com.community.common.service;

import com.community.common.core.*;
import com.community.common.util.BeanUtils;
import com.community.domain.core.Page;
import com.community.domain.core.PageAble;
import com.community.domain.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractCurdService<KEY, Q extends PageAble, M, T, Mapper> {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    protected Mapper mapper;

    public Response delete(KEY... keys) {
        return Response.success(((DeleteAble) mapper).delete(keys));
    }

    public Response deletePhysical(KEY key) {
        return Response.success(((DeletePhysicalAble) mapper).deletePhysical(key));
    }

    public Response listPage(Q q, Class<T> tClazz) {
        try {
            if (q == null) {
                return Response.fail("参数错误");
            }
            Page<M> page = ((ListAble) mapper).listPage(q);
            Page<T> returnPage = new Page<>();
            if (null != page) {
                returnPage.setHasMore(page.getHasMore());
                returnPage.setPagination(page.getPagination());
                returnPage.setData(BeanUtils.list2list(page.getData(), tClazz));
            }
            return Response.success(returnPage);
        } catch (Exception e) {
            logger.error("method[listPage]--q:{},tClazz:{}", q, tClazz, e);
            return Response.fail("查询失败");
        }

    }

    public Response listPageCount(Q q) {
        return Response.success(((ListAble) mapper).listPageCount(q));
    }

    public Response getByKey(KEY key) {
        return Response.success((M) ((GetByKeyAble) mapper).getByKey(key));
    }

    public Response getByKeys(KEY... keys) {
        return Response.success(((GetByKeysAble) mapper).getByKeys(keys));
    }

    public Response insert(M m) {
        return Response.success(((InsertAble) mapper).insert(m));
    }

    public Response insertSelective(M m) {
        return Response.success(((InsertSelectiveAble) mapper).insertSelective(m));
    }

    public Response update(M m) {
        return Response.success(((UpdateAble) mapper).update(m));
    }

    public Response updateByOptimisticLock(M m) {
        return Response.success(((UpdateByOptimisticLockAble) mapper).updateByOptimisticLock(m));
    }

    public Response updateSelective(M m) {
        return Response.success(((UpdateSelectiveAble) mapper).updateSelective(m));
    }


}
