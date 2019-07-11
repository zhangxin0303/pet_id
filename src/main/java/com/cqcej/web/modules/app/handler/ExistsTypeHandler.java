package com.cqcej.web.modules.app.handler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 类型转换，如果存在true，不存在false
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-04-30 下午9:59
 */
public class ExistsTypeHandler implements TypeHandler<Boolean> {
	@Override
	public void setParameter(PreparedStatement ps, int i, Boolean parameter, JdbcType jdbcType) throws SQLException {
	
	}
	
	@Override
	public Boolean getResult(ResultSet rs, String columnName) throws SQLException {
		int result = rs.getInt(columnName);
		return result > 0;
	}
	
	@Override
	public Boolean getResult(ResultSet rs, int columnIndex) throws SQLException {
		int result = rs.getInt(columnIndex);
		return result > 0;
	}
	
	@Override
	public Boolean getResult(CallableStatement cs, int columnIndex) throws SQLException {
		int result = cs.getInt(columnIndex);
		return result > 0;
	}
}
