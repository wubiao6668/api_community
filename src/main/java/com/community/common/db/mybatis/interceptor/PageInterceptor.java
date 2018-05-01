package com.community.common.db.mybatis.interceptor;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Plugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public abstract class PageInterceptor implements Interceptor {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    public static final String PAGE_SQL_KEY = "pageSqlKey";
    public static final String COUNT_SQL_KEY = "countSqlKey";

    public static final String DEFAULT_PAGE_SQL = "findPage";
    public static final String DEFAULT_COUNT_SQL = "Count";

    public static final String DEFAULT_LIMIT_COUNT_SQL = "LimitCount";

    protected String pageSql = DEFAULT_PAGE_SQL;
    protected String countSql = DEFAULT_COUNT_SQL;
    protected String limitCountSql = DEFAULT_LIMIT_COUNT_SQL;

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        if (properties != null) {
            String sqlId = properties.getProperty(PAGE_SQL_KEY);
            if (sqlId != null && !sqlId.trim().isEmpty()) {
                pageSql = sqlId;
            }

            sqlId = properties.getProperty(COUNT_SQL_KEY);
            if (sqlId != null && !sqlId.trim().isEmpty()) {
                countSql = sqlId;
            }
        }
    }

    protected String buildCountStatement(String statement) {
        if (statement == null) {
            return "count";
        }
        return statement + countSql;
    }

    protected String buildLimitCountStatement(String statement) {
        if (statement == null) {
            return "limitCount";
        }
        return statement + limitCountSql;
    }

    public static void main(String[] args) {
        String statement = "dsdfsByPage";
        System.out.println(statement.substring(0, statement.lastIndexOf("byPage")));
    }
}
