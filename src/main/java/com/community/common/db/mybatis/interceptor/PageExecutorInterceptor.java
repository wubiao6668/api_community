package com.community.common.db.mybatis.interceptor;

import com.community.common.enums.PageAbleEnum;
import com.community.domain.core.Page;
import com.community.domain.core.PageAble;
import com.community.domain.core.Pagination;
import com.community.domain.core.PaginationAble;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Optional;

@Intercepts({@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
public class PageExecutorInterceptor extends PageInterceptor {

    private static final int MAPPED_STATEMENT_INDEX = 0;
    private static final int QUERY_PARAM_INDEX = 1;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        Object queryParam = invocation.getArgs()[QUERY_PARAM_INDEX];
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[MAPPED_STATEMENT_INDEX];
        String statementId = mappedStatement.getId();
        //判断是否要分页
        PaginationAble pageQuery = null;
        if (null == queryParam || !PageAble.class.isAssignableFrom(queryParam.getClass()) || !statementId.endsWith("Page")) {
            //无需分页直接返回
            return invocation.proceed();
        }
        pageQuery = ((PageAble) queryParam).getPagination();
        if (null == pageQuery) {
            pageQuery = new PaginationAble();
        }
        if (null == pageQuery.getPageType()) {
            pageQuery.setPageType(PageAbleEnum.IS_MORE.getCode());
        }
        //分页逻辑开始
        Executor executor = (Executor) invocation.getTarget();
        Configuration configuration = mappedStatement.getConfiguration();
        //判断是否查询总条数
        Integer countType = pageQuery.getPageType();
        boolean isCount = PageAbleEnum.TOTAL.getCode().equals(countType) || PageAbleEnum.LIMIT.getCode().equals(countType);
        Integer count = null;
        Pagination pagination = null;

        int page = pageQuery.getPage();
        int pageSize = pageQuery.getPageSize();
        int offset = (page - 1) * pageSize;
        int limit = pageSize;
        Boolean hasMore = false;
        List dataList = null;
        //查询count
        if (isCount) {
            List<Integer> counts = null;
            if (PageAbleEnum.TOTAL.getCode().equals(countType)) {
                String countStatementId = buildCountStatement(statementId);
                counts = executor.query(configuration.getMappedStatement(countStatementId), queryParam, RowBounds.DEFAULT, null);
                count = CollectionUtils.isNotEmpty(counts) ? counts.get(0) : 0;
                hasMore = (offset + limit) < count ? true : false;
            } else {
                String countStatementId = buildLimitCountStatement(statementId);
                counts = executor.query(configuration.getMappedStatement(countStatementId), queryParam, RowBounds.DEFAULT, null);
                count = CollectionUtils.isNotEmpty(counts) ? page * pageSize + counts.get(0) : 0;
                int limitPageSize = pageQuery.getLimitPageSize();
                hasMore = limitPageSize == count ? true : false;
            }
            RowBounds rowBounds = new RowBounds(offset, limit);
            pagination = new Pagination(page, pageSize, count);
            dataList = executor.query(mappedStatement, queryParam, rowBounds, null);
        } else {
            dataList = (List) invocation.proceed();
            hasMore = null != dataList && dataList.size() == pageSize ? true : false;
        }
        //返回结果
        List<Page> result = Lists.newArrayList();
        Page pageInfo = new Page();
        pageInfo.setHasMore(hasMore);
        pageInfo.setPagination(Optional.ofNullable(pagination));
        pageInfo.setData(Optional.ofNullable(dataList));
        result.add(pageInfo);
        return result;
    }
}
