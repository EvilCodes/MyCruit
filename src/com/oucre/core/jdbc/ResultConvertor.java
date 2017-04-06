package com.oucre.core.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 映射jdbc返回的数据类型到自定义类型的转换器
 */
public interface ResultConvertor {

	/**
	 * 自定义转换逻辑
	 * @param resultSet 持有当前行数据的ResultSet对象
	 * @param rowIndex 当前使用的行索引
	 * @return
	 * @since 2015年1月9日
	 */
	public Object convert(ResultSet resultSet,int rowIndex) throws SQLException; 
}
