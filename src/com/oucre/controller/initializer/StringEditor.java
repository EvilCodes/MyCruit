package com.oucre.controller.initializer;

import org.springframework.beans.propertyeditors.PropertiesEditor;

public class StringEditor extends PropertiesEditor {
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (text != null && text.length() > 0) {
			setValue(text.trim());
		} else {
			setValue(null);
		}
	} 

	@Override
	public String getAsText() {
		return getValue().toString();
	}
}