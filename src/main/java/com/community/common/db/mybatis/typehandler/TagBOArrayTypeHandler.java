package com.community.common.db.mybatis.typehandler;

import com.community.common.util.JacksonUtil;
import com.community.domain.bo.TagBO;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(TagBO[].class)
public class TagBOArrayTypeHandler extends BaseTypeHandler<TagBO[]> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, TagBO[] parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, JacksonUtil.toJSONString(parameter));
    }

    @Override
    public TagBO[] getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String tagJson = rs.getString(columnName);
        return jsonToTags(tagJson);
    }

    @Override
    public TagBO[] getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String tagJson = rs.getString(columnIndex);
        return jsonToTags(tagJson);
    }

    @Override
    public TagBO[] getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String tagJson = cs.getString(columnIndex);
        return jsonToTags(tagJson);
    }

    private TagBO[] jsonToTags(String tagJson) {
        if (StringUtils.isEmpty(tagJson)) {
            return null;
        }
        TagBO[] tagBOS = JacksonUtil.parseObject(tagJson, new TypeReference<TagBO[]>() {});
        return tagBOS;
    }
}
