package com.oucre.core.mode.logger;

public class LoggerMessage {
	private String code;// 错误代码
	private String message; // 错误消息
	private String classs;// 异常类
	private String method;// 方法

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getClasss() {
		return classs;
	}

	public void setClasss(String classs) {
		this.classs = classs;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	@Override
	public String toString() {
		return "LoggerMessage [错误代码=" + code + ", 错误消息=" + message + ", 错误类="
				+ classs + ", 错误方法=" + method + "]";
	}

	public LoggerMessage(String code, String message, String classs,
			String method) {
		super();
		this.code = code;
		this.message = message;
		this.classs = classs;
		this.method = method;
	}

	public LoggerMessage() {
		super();
	}

}
