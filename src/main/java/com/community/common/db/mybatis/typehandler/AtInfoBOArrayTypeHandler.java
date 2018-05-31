package com.community.common.db.mybatis.typehandler;

import com.community.common.util.JacksonUtil;
import com.community.domain.bo.AtInfoBO;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(AtInfoBO[].class)
public class AtInfoBOArrayTypeHandler extends BaseTypeHandler<AtInfoBO[]> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, AtInfoBO[] parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, JacksonUtil.toJSONString(parameter));
    }

    @Override
    public AtInfoBO[] getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String tagJson = rs.getString(columnName);
        return jsonToTags(tagJson);
    }

    @Override
    public AtInfoBO[] getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String tagJson = rs.getString(columnIndex);
        return jsonToTags(tagJson);
    }

    @Override
    public AtInfoBO[] getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String tagJson = cs.getString(columnIndex);
        return jsonToTags(tagJson);
    }

    private AtInfoBO[] jsonToTags(String tagJson) {
        if (StringUtils.isEmpty(tagJson)) {
            return null;
        }
        AtInfoBO[] atInfoBOS = JacksonUtil.parseObject(tagJson, new TypeReference<AtInfoBO[]>() {
        });
        return atInfoBOS;
    }
}
