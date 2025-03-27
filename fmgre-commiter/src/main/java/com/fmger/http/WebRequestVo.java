package com.fmger.http;

import java.util.HashMap;
import java.util.Map;

import org.htmlunit.HttpMethod;


public class WebRequestVo {

	public String url;
	
	public HttpMethod method = HttpMethod.GET;
	
	public HttpContentTypes contentType = HttpContentTypes.html;
	
	public HttpAccepts accept = HttpAccepts.htmlpage;
	
	public Map<String, String> headers = new HashMap<String, String>();
	
	public Map<String, Object> querys = new HashMap<String, Object>();
	
	public Object payload;
	
	public WebRequestVo(String url) {
		this(url, HttpMethod.GET);
	}
	public WebRequestVo(String url, HttpMethod method) {
		this.url = url;
		this.method = method;
	}
	
	public WebRequestVo asJsonRequest() {
		this.contentType = HttpContentTypes.json;
		this.accept = HttpAccepts.json;
		return this;
	}
 
	public String getDesc() {
		return this.url;
	}

	public WebRequestVo setUrl(String url) {
		this.url = url;
		return this;
	}

	public WebRequestVo setMethod(HttpMethod method) {
		this.method = method;
		return this;
	}

	public WebRequestVo setContentType(HttpContentTypes contentType) {
		this.contentType = contentType;
		return this;
	}

	public WebRequestVo setAccept(HttpAccepts accept) {
		this.accept = accept;
		return this;
	}

	public WebRequestVo setHeaders(Map<String, String> headers) {
		this.headers.putAll(headers);
		return this;
	}

	public WebRequestVo setHeader(String key, String value) {
		this.headers.put(key, value);
		return this;
	}

	public WebRequestVo setPayload(Object payload) {
		this.payload = payload;
		return this;
	}
	public WebRequestVo setQuerys(Map<String, Object> querys) {
		this.querys.putAll(querys);
		return this;
	}

	public WebRequestVo setQuery(String key, Object query) {
		this.querys.put(key, query);
		return this;
	}
	
}
