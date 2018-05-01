package com.community.common.db.mybatis;

/**
 * Mysql数据库语言
 */
public class MySql5Dialect implements Dialect {

    public String getLimitString(String sql, int offset, int limit) {
        return getLimitString(sql, offset, Integer.toString(offset), Integer.toString(limit));
    }

    private String getLimitString(String sql, int offset, String offsetPlaceholder, String limitPlaceholder) {
        StringBuilder builder = new StringBuilder(sql);
        builder.append(" limit ");
        if (offset > 0) {
            builder.append(offsetPlaceholder).append(",").append(limitPlaceholder);
        } else {
            builder.append(limitPlaceholder);
        }
        return builder.toString();
    }

}
