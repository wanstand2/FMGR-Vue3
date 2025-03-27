package com.fmger.http;

import java.lang.reflect.Type;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class HttpWebsite<V> implements ISelfIdent{

    static Logger log = LoggerFactory.getLogger(HttpWebsite.class);

	protected String host = "localhost"; //网站地址
	protected int port = 443; //网站端口
	protected String protocal= "https"; //协议
	protected String proxyHost = null;
	protected int proxyPort = 8080;
	protected int timeOut = 15000;
	protected String apath = "";
	protected String proxyUsername = null;
	protected String proxyPassword = null;
	
	public abstract String getIdent();
	
	private HttpWebsite() {
		super();
	}
	
	public HttpWebsite(String protocal, String host, int port) {
		this();
		this.protocal = protocal;
		this.host = host;
		this.port = port;
	}
	
	public HttpWebsite(String host, int port) {
		this("https", host, port);
	}
	
	public HttpWebsite(String host) {
		this("https", host, 443);
	}
	
	public HttpWebsite<V> setProxy(String host, int port) {
		this.proxyHost = host;
		this.proxyPort = port;
		return this;
	}
	
	public HttpWebsite<V> setProxyCredentials(String username, String password) {
		this.proxyUsername = username;
		this.proxyPassword = password;
		return this;
	}
	
	//若需登录，则写登录逻辑
	protected abstract void login(WebRequestVo vo);
	
	protected abstract <T> T _doRequest(WebRequestVo vo, Type clz);

	public <T> T doRequest(WebRequestVo vo, Type clz) {
		this.login(vo);
		return this._doRequest(vo, clz);
	}
	
	public abstract void close();
	
	public <T> T request(WebRequestVo vo, Type clz) {
		return this.doRequest(vo, clz);
	}
	
	protected abstract <T> T parse(V p, Type clz);

	protected void syslog(String url, String format, Object...objs) {
		log.info(String.format("[%s][url=%s]"+format, 
				this.getClass().getSimpleName(), 
				url,
				objs));
	}
}
