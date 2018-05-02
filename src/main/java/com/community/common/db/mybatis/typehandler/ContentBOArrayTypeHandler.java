package com.community.common.db.mybatis.typehandler;

import com.community.common.util.JacksonUtil;
import com.community.domain.bo.ContentBO;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(ContentBO[].class)
public class ContentBOArrayTypeHandler extends BaseTypeHandler<ContentBO[]> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, ContentBO[] parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, JacksonUtil.toJSONString(parameter));
    }

    @Override
    public ContentBO[] getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String contentJson = rs.getString(columnName);
        return jsonToContents(contentJson);
    }

    @Override
    public ContentBO[] getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String contentJson = rs.getString(columnIndex);
        return jsonToContents(contentJson);
    }

    @Override
    public ContentBO[] getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String contentJson = cs.getString(columnIndex);
        return jsonToContents(contentJson);
    }

    private ContentBO[] jsonToContents(String contentJson) {
        if (StringUtils.isEmpty(contentJson)) {
            return null;
        }
        ContentBO[] contentBOS = JacksonUtil.parseObject(contentJson, new TypeReference<ContentBO[]>() {
        });
        return contentBOS;
    }
}
