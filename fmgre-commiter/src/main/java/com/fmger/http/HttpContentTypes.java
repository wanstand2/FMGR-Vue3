package com.fmger.http;

public enum HttpContentTypes {
	html("text/html;charset=utf-8"),
	json("application/json;charset=UTF-8"),
	post("application/x-www-form-urlencoded");
	
	private String value;
	
	private HttpContentTypes(String value) {
		this.value = value;
	}
	
	public String value() {
		return this.value;
	}
}
