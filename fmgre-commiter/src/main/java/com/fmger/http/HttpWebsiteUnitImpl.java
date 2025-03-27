package com.fmger.http;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.htmlunit.BrowserVersion;
import org.htmlunit.DefaultCredentialsProvider;
import org.htmlunit.FailingHttpStatusCodeException;
import org.htmlunit.NicelyResynchronizingAjaxController;
import org.htmlunit.Page;
import org.htmlunit.WebClient;
import org.htmlunit.WebRequest;
import org.htmlunit.html.DomNode;
import org.htmlunit.html.HtmlPage;
import org.htmlunit.util.NameValuePair;

import com.fmger.LocalAccess;
import com.fmger.http.exception.WebsiteConnectException;
import com.fmger.http.exception.WebsiteParserException;
import com.fmger.utils.Filer;
import com.google.gson.Gson;
import com.ruoyi.common.utils.StringUtils;

public abstract class HttpWebsiteUnitImpl extends HttpWebsite<Page>{
    
	public HttpWebsiteUnitImpl(String host) {
		super(host);
	}
	
	public HttpWebsiteUnitImpl(String protocal, String host, int port) {
		super(protocal, host, port);
	}
	
	public HttpWebsiteUnitImpl(String host, int port) {
		super(host, port);
	}
	

	private static Map<String, String> headers = new HashMap<String,String>();
	{
		headers.put("Accept-Encoding","gzip");
		headers.put("Accept-Language","zh-CN,zh;q=0.9,en;q=0.8");
		headers.put("Connection","keep-alive");
		headers.put("Upgrade-Insecure-Requests","1");
		headers.put("User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_2) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.4 Safari/605.1.15");
	}
	
	private WebClient wc;
	
	protected WebClient getWc() {
		if(this.wc == null) {
			this.wc = this.genWc();
		}
		return this.wc;
	}
	
	private WebClient genWc() {
		WebClient wc;
		if(StringUtils.isNotEmpty(this.proxyHost)) {
			wc = new WebClient(BrowserVersion.CHROME, this.proxyHost, this.proxyPort);
		} else {
			wc = new WebClient(BrowserVersion.CHROME);
		}
        wc.getOptions().setThrowExceptionOnScriptError(false);//当JS执行出错的时候是否抛出异常, 这里选择不需要
        wc.getOptions().setThrowExceptionOnFailingStatusCode(false);//当HTTP的状态非200时是否抛出异常, 这里选择不需要
		wc.getOptions().setCssEnabled(false);
        wc.getOptions().setJavaScriptEnabled(false); //很重要，启用JS
        wc.getOptions().setRedirectEnabled(true);
        wc.getOptions().setUseInsecureSSL(true);
        wc.getOptions().setTimeout(timeOut);
        if(this.proxyUsername != null) {
        	final DefaultCredentialsProvider credentialsProvider = (DefaultCredentialsProvider) wc.getCredentialsProvider();
        	credentialsProvider.addCredentials(this.proxyUsername, this.proxyPassword, this.proxyHost, this.proxyPort, DefaultCredentialsProvider.ANY_REALM);
        }
        wc.setAjaxController(new NicelyResynchronizingAjaxController());//很重要，设置支持AJAX
        return wc;
	}

	protected boolean checkPage(Page p) { return true;}

	protected <T> T _doRequest(WebRequestVo vo, Type clz) {
		WebClient wc = this.getWc();
		WebRequest wr = this.conv(vo);
		Page p = null;
		try {
			p = wc.getPage(wr);
			//System.out.println("resolveDone1 = "+t.url+":"+t);
			if(vo.accept.equals(HttpAccepts.htmlpage)) {
				wc.waitForBackgroundJavaScript(200);
			}
        } catch (FailingHttpStatusCodeException | IOException e) {
        	throw new WebsiteConnectException(e);
		}
		if(!checkPage(p)) {
			return null;
		}
		//原始Page类对象
		if(clz == null || clz.getTypeName().startsWith("org.htmlunit")) {
			return (T)p;
		}
		try {
			//JSON类，默认自行转化
			if(vo.accept.equals(HttpAccepts.json)) {
				return getGson().fromJson(p.getWebResponse().getContentAsString(), clz);
			}
			//Customize对象
			return this.parse(p, clz);
		} catch(Exception e) {
			throw new WebsiteParserException(e);
		}
	}
	
	public void close() {
		if(this.wc == null) return;
    	try {
    		this.wc.close();
    		this.wc = null;
    	} catch (Exception e) {}
	}
	
	public Gson getGson() {
		return LocalAccess.get(Gson.class);
	}
	
	private WebRequest conv(WebRequestVo vo) {
		WebRequest wr = null;
		String url = this.protocal+"://"+this.host+(this.port==80 || this.port==443?"":":"+this.port)+vo.url;
		try {
			wr = new WebRequest(
					new URL(url), vo.method);
		} catch (MalformedURLException e) {
			log.error("URL:"+url, e);
		}
		wr.setCharset(Charset.forName("UTF-8"));
		if(vo.headers != null) {
			wr.setAdditionalHeaders(vo.headers);
		}
		wr.setAdditionalHeader("Accept", vo.accept.value());
		wr.setAdditionalHeader("Content-type", vo.contentType.value());
		if(vo.payload != null
				&& vo.accept.equals(HttpAccepts.json)) {
			String content = getGson().toJson(vo.payload);
			wr.setRequestBody(content);
		}
		if(vo.querys.size() == 0) return wr;
		List<NameValuePair> pairs = new ArrayList<NameValuePair>();
		pairs.addAll(wr.getRequestParameters());
		for(Entry<String, Object> e:vo.querys.entrySet()) {
			Object o = e.getValue();
			if(o == null) {
				continue;
			}
			/*
			if(e == null) {
				pairs.add(new NameValuePair(e.getKey(), ""));
				continue;
			}
			*/
			if(o instanceof List) {
				List ls = (List)o;
				for(Object l:ls) {
					pairs.add(
							new NameValuePair(e.getKey(), StringUtils.objectToString(l)));
				}
				continue;
			}
			if(o.getClass().isArray()) {
				Object[] obs = (Object[])o;
				for(Object ob:obs) {
					pairs.add(
							new NameValuePair(e.getKey(), StringUtils.objectToString(ob)));
				}
				continue;
			}
			pairs.add(new NameValuePair(e.getKey(), StringUtils.objectToString(o)));
		}
		wr.setRequestParameters(pairs);
		return wr;
	}
	
	public void dumpFile(String name, HtmlPage page) {
		File f = new File("dump/"+this.getClass().getSimpleName());
		f.mkdirs();
		try {
			Filer.writeFileContents(new File(f.getPath() + File.separator + name + ".txt"), new String[] {
					page.getUrl().toString(), page.asXml()});
		} catch (IOException e) {
			log.error("dump文件异常", e);
		}
	}
	
	protected DomNode getByXPath(DomNode node, String xpath) {
		List<DomNode> hes = node.getByXPath(xpath);
		if(hes.size() > 0) {
			return hes.get(0);
		}
		return null;
	}
}
