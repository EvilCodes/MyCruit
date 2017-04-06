package com.oucre.controller.initializer;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.propertyeditors.PropertiesEditor;
import org.springframework.util.StringUtils;

public class TimestampEditor extends PropertiesEditor {

	public void setAsText(String text) throws IllegalArgumentException {
		if (StringUtils.hasText(text)) {
			Timestamp timeUseForSearchInDatabase = null;
			try {
				setValue(timeUseForSearchInDatabase = new Timestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(text).getTime()));
			} catch (ParseException e) {
				e.printStackTrace();
				setValue(null);
			}
		} else {
			setValue(null);
		}
	}
}
