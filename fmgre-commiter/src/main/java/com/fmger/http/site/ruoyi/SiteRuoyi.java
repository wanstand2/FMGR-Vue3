package com.fmger.http.site.ruoyi;

import java.lang.reflect.Type;

import org.htmlunit.HttpMethod;
import org.htmlunit.Page;

import com.fmger.http.HttpWebsiteUnitImpl;
import com.fmger.http.WebRequestVo;
import com.fmger.http.site.ruoyi.vo.RyLoginToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ruoyi.common.core.domain.model.LoginBody;

public class SiteRuoyi extends HttpWebsiteUnitImpl {

	private static LoginBody body = new LoginBody();
	{
		body.setUsername("admin");
		body.setPassword("admin123");
	}
	
	private RyLoginToken token = null;
	
	public SiteRuoyi() {
		super("http", "localhost", 80);
		this.timeOut = 0;
	}

	@Override
	protected void login(WebRequestVo vo) {
		if(this.token == null) {
			WebRequestVo login = new WebRequestVo("/dev-api/login", HttpMethod.POST)
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
	
}
