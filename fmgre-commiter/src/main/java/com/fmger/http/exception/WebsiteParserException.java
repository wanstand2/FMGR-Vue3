package com.fmger.http.exception;

public class WebsiteParserException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public WebsiteParserException() {
		super();
	}

	public WebsiteParserException(String msg) {
		super(msg);
	}
	
    public WebsiteParserException(Exception e) {
		super(e);
	}

}
