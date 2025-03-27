package com.fmger.http;

public enum HttpAccepts {
	htmlpage("text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7"),
	image("image/jpeg,image/avif,image/webp,image/apng,image/svg+xml,image/*,*/*;q=0.8"),
	json("application/json;q=0.9,text/plain;q=0.8,*/*;q=0.7");
	
	private String value;
	
	private HttpAccepts(String value) {
		this.value = value;
	}
	
	public String value() {
		return this.value;
	}
}
