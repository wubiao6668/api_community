package com.community.common.db.mybatis.typehandler;

import com.community.common.util.JacksonUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ObjectJsonTypeHandler<E> extends BaseTypeHandler<E> {

    private final Class<E> type;

    public ObjectJsonTypeHandler(Class<E> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.type = type;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, JacksonUtil.toJSONString(parameter));
    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String rsJsonStr = rs.getString(columnName);
        if (StringUtils.isEmpty(rsJsonStr)) {
            return null;
        }
        return JacksonUtil.parseObject(rsJsonStr, type);
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String rsJsonStr = rs.getString(columnIndex);
        if (StringUtils.isEmpty(rsJsonStr)) {
            return null;
        }
        return JacksonUtil.parseObject(rsJsonStr, type);
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String rsJsonStr = cs.getString(columnIndex);
        if (StringUtils.isEmpty(rsJsonStr)) {
            return null;
        }
        return JacksonUtil.parseObject(rsJsonStr, type);
    }
}
