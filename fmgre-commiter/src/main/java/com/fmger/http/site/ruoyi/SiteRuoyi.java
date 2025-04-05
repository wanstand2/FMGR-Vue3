package com.fmger.http.site.ruoyi;

import java.lang.reflect.Type;

import org.htmlunit.HttpMethod;
import org.htmlunit.Page;

import com.fmger.http.HttpWebsiteUnitImpl;
import com.fmger.http.WebRequestVo;
import com.fmger.http.site.ruoyi.vo.RyLoginToken;
import com.ruoyi.common.core.domain.model.LoginBody;

public class SiteRuoyi extends HttpWebsiteUnitImpl {
	
	private static class Profile {
		public String username;
		public String password;
		public String protocal;
		public String host;
		public Integer port;
		public String api;
		public Profile() {}
		public Profile(String protocal, String host, Integer port, String username, String password, String api) {
			super();
			this.username = username;
			this.password = password;
			this.protocal = protocal;
			this.host = host;
			this.port = port;
			this.api = api;
		}
		
	}
	
	public static Profile LOCAL = new Profile("http", "localhost", 80, "admin", "admin123", "dev-api");
	public static Profile ZZSELECT = new Profile("http", "47.115.226.202", 80, "admin", "shakj1^&31gj1k", "prod-api");
	public static Profile profile = null;
	
	

	private static LoginBody body = new LoginBody();
	{
		body.setUsername(profile.username);
		body.setPassword(profile.password);
//		body.setPassword("shakj1^&31gj1k");
	}
	
	private RyLoginToken token = null;
	
	public SiteRuoyi() {
		super(profile.protocal, profile.host, profile.port);
//		super("http", "47.115.226.202", 80);
		this.timeOut = 0;
	}

//	public String api = "dev-api";
//	public String api = "prod-api";

	@Override
	protected void login(WebRequestVo vo) {
		if(this.token == null) {
			WebRequestVo login = new WebRequestVo("/" + getApi() + "/login", HttpMethod.POST)
					.asJsonRequest()
					.setPayload(body);
			this.token = this._doRequest(login, RyLoginToken.class);
			try {
				Thread.sleep(30L);
			} catch (InterruptedException e) {
			}
		}
		vo.setHeader("Authorization", "Bearer " + token.token);
	}

	@Override
	protected <T> T parse(Page p, Type clz) {
		return null;
	}

	@Override
	public String getIdent() {
		return "ruoyi";
	}

	public String getApi() {
		return profile.api;
	}
	
}
