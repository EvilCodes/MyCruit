package com.oucre.controller.initializer;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

public class MyWebBinding implements WebBindingInitializer {
	@Override
	public void initBinder(WebDataBinder binder, WebRequest rqeust) {
		binder.registerCustomEditor(Date.class, new DateEditor());
		binder.registerCustomEditor(Timestamp.class, new TimestampEditor());
		binder.registerCustomEditor(Integer.class, new IntegerEditor());
		binder.registerCustomEditor(Long.class, new LongEditor());
		binder.registerCustomEditor(Double.class, new DoubleEditor());
		binder.registerCustomEditor(String.class, new StringEditor());
	}
}
