package com.community.common.db.mybatis;

/**
 * 支持的数据库语言
 */
public interface Dialect {
    public static enum Type {
        MYSQL,
        ORACLE
    }

    public String getLimitString(String sql, int skipResults, int maxResults);
}