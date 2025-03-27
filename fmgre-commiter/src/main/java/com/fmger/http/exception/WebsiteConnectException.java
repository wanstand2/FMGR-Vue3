package com.fmger.http.exception;

public class WebsiteConnectException extends RuntimeException {
    private static final long serialVersionUID = 1L;
	
	public WebsiteConnectException() {
		super();
	}

	public WebsiteConnectException(String msg) {
		super(msg);
	}
	
    public WebsiteConnectException(Exception e) {
		super(e);
	}

}
