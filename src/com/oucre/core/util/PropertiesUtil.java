package com.oucre.core.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

public class PropertiesUtil {
	/**
	 * 根据名称获得名称对应的编号 或者反之亦可。
	 * 
	 * @param c
	 *            上下文对象
	 * @param strProName
	 *            配置信息的名字
	 * @param strProName
	 *            配件的名字
	 */
	public static String getProperties(String strPropertiesName,
			String strName) {
		Properties properties = new Properties();
		try {
			// 通过activity中的context攻取setting.properties的FileInputStream
			InputStream in = new BufferedInputStream(new FileInputStream(
					strPropertiesName));
			properties.load(in);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return properties.get(strName).toString();
	}

	public static List<String> getAllKeys(String strPropertiesName) {
		List<String> list = new ArrayList<String>();
		Properties props = new Properties();
		try {
			InputStream in = new BufferedInputStream(new FileInputStream(
					strPropertiesName));
			props.load(in);
			Enumeration en = props.propertyNames();
			while (en.hasMoreElements()) {
				String key = (String) en.nextElement();
				// 根据key获得value
//				String value = props.getProperty(key);
				list.add(key);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}