package com.oucre.core.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 持有jdbc数据到自定义类型的转换配置信息
 * <p>
 * 内置了基本类型转换器
 */
public class TypeMap {
	
	private static TypeMap instance = null;
	private static Map<String,ResultConvertor> typeMap = null;
	private TypeMap(){
	}
	
	private static synchronized TypeMap getInstance(){
		if(instance == null)
			instance = new TypeMap();
		return instance;
	}
	
	static{
		getInstance();
		typeMap = new HashMap<String,ResultConvertor>();
		typeMap.put(Short.class.getName(), instance.new ShortConvertor());
		typeMap.put(Integer.class.getName(),  instance.new IntegerConvertor());
		typeMap.put(Long.class.getName(), instance.new LongConvertor());
		typeMap.put(Double.class.getName(), instance.new DoubleConvertor());
		typeMap.put(Float.class.getName(), instance.new FloatConvertor());
		typeMap.put(String.class.getName(), instance.new StringConvertor());
	}
	
	public static ResultConvertor getMap(String name){
		return typeMap.get(name);
	}
	
	public static synchronized void setMap(String name,ResultConvertor convertor){
		typeMap.put(name,convertor);
	}
	
	public static synchronized void removeMap(String name){
		typeMap.remove(name);
	}

	/*****************************************
	 					内置转换器
	 *****************************************/
	
	private class IntegerConvertor implements ResultConvertor{
		public Object convert(ResultSet resultSet, int rowIndex) throws SQLException{
			return resultSet.getInt(1);
		}
	}
	private class LongConvertor implements ResultConvertor{
		public Object convert(ResultSet resultSet, int rowIndex) throws SQLException{
			return resultSet.getLong(1);
		}
	}
	private class DoubleConvertor implements ResultConvertor{
		public Object convert(ResultSet resultSet, int rowIndex) throws SQLException{
			return resultSet.getDouble(1);
		}
	}
	private class StringConvertor implements ResultConvertor{
		public Object convert(ResultSet resultSet, int rowIndex) throws SQLException{
			return resultSet.getString(1);
		}
	}
	private class ShortConvertor implements ResultConvertor{
		public Object convert(ResultSet resultSet, int rowIndex) throws SQLException{
			return resultSet.getShort(1);
		}
	}
	private class FloatConvertor implements ResultConvertor{
		public Object convert(ResultSet resultSet, int rowIndex) throws SQLException{
			return resultSet.getFloat(1);
		}
	}
}