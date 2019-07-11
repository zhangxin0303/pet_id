package com.cqcej.web.modules.app.handler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * DESCRIPTION
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-07-30 16:45
 */
public class UrlTypeHandler implements TypeHandler<List<String>> {
	
	@Override
	public void setParameter(PreparedStatement ps, int i, List<String> parameter, JdbcType jdbcType) throws SQLException {
	
	}
	
	@Override
	public List<String> getResult(ResultSet rs, String columnName) throws SQLException {
		// 地址字符串，用,分隔
		String result = rs.getString(columnName);
		return getResult(result);
	}
	
	@Override
	public List<String> getResult(ResultSet rs, int columnIndex) throws SQLException {
		String result = rs.getString(columnIndex);
		return getResult(result);
	}
	
	@Override
	public List<String> getResult(CallableStatement cs, int columnIndex) throws SQLException {
		String result = cs.getString(columnIndex);
		return getResult(result);
	}
	
	private List<String> getResult(String result) {
		if (result == null) {
			return new ArrayList<>();
		} else {
			String[] urls = result.split(",");
			return new ArrayList<>(Arrays.asList(urls));
		}
	}
}
