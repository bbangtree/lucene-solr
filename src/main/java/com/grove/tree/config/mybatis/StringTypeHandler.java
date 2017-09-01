package com.grove.tree.config.mybatis;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

@MappedJdbcTypes(value={JdbcType.CLOB})
public class StringTypeHandler extends BaseTypeHandler<String> {

	@Override
	public String getNullableResult(CallableStatement cs, int parameterIndex) throws SQLException {
		return cs.getString(parameterIndex);
	}

	@Override
	public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		return rs.getString(columnIndex);
	}

	@Override
	public String getNullableResult(ResultSet rs, String columnLabel) throws SQLException {
		return rs.getString(columnLabel);
	}

	@Override
	@SuppressWarnings("deprecation")
	public void setNonNullParameter(PreparedStatement ps, int parameterIndex, String x, JdbcType jdbcType) throws SQLException {
		ps.setString(parameterIndex, ObjectUtils.toString(x, ""));
	}

}
