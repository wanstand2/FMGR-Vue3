package com.fmger.http;

import java.util.Date;

import org.htmlunit.util.Cookie;

public class HttpCookie {

	public HttpCookie() {
		super();
	}
	
	public HttpCookie(Cookie ck) {
		this();
		this.domain = ck.getDomain();
		this.name = ck.getName();
		this.value = ck.getValue();
		this.path = ck.getPath();
		this.expires = ck.getExpires().getTime();
		this.secure = ck.isSecure();
		this.httpOnly = ck.isHttpOnly();
	}
	
	public Cookie toCookie() {
		return new Cookie(domain, name, value, path, new Date(expires),
		        secure, httpOnly);
	}
	
	public String domain;
	public String name;
	public String value;
	public String path; 
	public Long expires;
    public boolean secure = false;
    public boolean httpOnly = false;
}
