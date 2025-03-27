package com.fmger.http;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.htmlunit.BrowserVersion;
import org.htmlunit.FailingHttpStatusCodeException;
import org.htmlunit.NicelyResynchronizingAjaxController;
import org.htmlunit.Page;
import org.htmlunit.WebClient;
import org.htmlunit.WebRequest;

import com.fmger.http.exception.WebsiteConnectException;
import com.fmger.utils.Filer;
import com.fmger.utils.ParserCollection;
import com.ruoyi.common.utils.StringUtils;

public class HttpDownload implements ISelfIdent{

	private WebClient wc;
	protected String proxyHost = null;
	protected int proxyPort = 8080;

	private static Map<String, String> headers = new HashMap<String,String>();
	{
		headers.put("Accept-Encoding","gzip, deflate, br");
		headers.put("Accept-Language","zh-CN,zh;q=0.9");
//		headers.put("Connection","keep-alive");
		headers.put("Upgrade-Insecure-Requests","1");
//		headers.put("User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36");
		headers.put("User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_2) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.4 Safari/605.1.15");
	}
	
	public HttpDownload() {
		super();
	}
	public HttpDownload(String host, int port) {
		this();
		this.proxyHost = host;
		this.proxyPort = port;
	}
	private WebClient genWc() {
		WebClient wc;
		if(StringUtils.isNotEmpty(this.proxyHost)) {
			wc = new WebClient(BrowserVersion.CHROME, this.proxyHost, this.proxyPort);
		} else {
			wc = new WebClient(BrowserVersion.CHROME);
		}
		wc.getCookieManager().setCookiesEnabled(true);
        wc.getOptions().setThrowExceptionOnScriptError(false);//当JS执行出错的时候是否抛出异常, 这里选择不需要
        wc.getOptions().setThrowExceptionOnFailingStatusCode(false);//当HTTP的状态非200时是否抛出异常, 这里选择不需要
		wc.getOptions().setCssEnabled(false);
        wc.getOptions().setJavaScriptEnabled(false); //很重要，启用JS
        wc.setAjaxController(new NicelyResynchronizingAjaxController());//很重要，设置支持AJAX
        return wc;
	}
	
	protected WebClient getWc() {
		if(this.wc == null) {
			this.wc = this.genWc();
		}
		return this.wc;
	}
	
	public void close() {
		if(this.wc == null) return;
    	try {
    		this.wc.close();
    		this.wc = null;
    	} catch (Exception e) {}
	}

	private boolean isNotValid(byte[] bs) {
		if(bs[0] == (byte)0x3c && bs[1] == (byte)0x68 && bs[2] == (byte)0x74
				&& bs[3] == (byte)0x6d && bs[4] == (byte)0x6c && bs[5] == (byte)0x3e) {
			return false;
		}
		if(bs[0] == (byte)0x3c && bs[1] == (byte)0x21 && bs[2] == (byte)0x44
				&& bs[3] == (byte)0x4f && bs[4] == (byte)0x43 && bs[5] == (byte)0x54) {
			return false;
		}
		return true;
	}

	public int download(String url, File file) {
		return this.download(url, file, null);
	}
	public int download(String url, File file, Map<String, String> exheaders) {
		InputStream is = null;
		OutputStream os = null;
		boolean valid = true;
		try {
			WebRequest wr = new WebRequest(new URL(url));
			if(ParserCollection.isImage(Filer.getExt(file))) {
				wr.setAdditionalHeader("Accept", HttpAccepts.htmlpage.name());
			} else {
				wr.setAdditionalHeader("Accept", HttpAccepts.htmlpage.name());
			}
			for(Entry<String, String> e:headers.entrySet()) {
				wr.setAdditionalHeader(e.getKey(), e.getValue());
			}
			if(exheaders != null) {
				for(Entry<String, String> e:exheaders.entrySet()) {
					wr.setAdditionalHeader(e.getKey(), e.getValue());
				}
			}
			Page p = this.getWc().getPage(wr);
			is = p.getWebResponse().getContentAsStream();
			os = new FileOutputStream(file);
			byte[] buf = new byte[4096];
			int len, size = 0;
			while(valid && (len = is.read(buf)) != -1) {
				if(size == 0) {
					valid = this.isNotValid(buf);
					if(!valid) {
						System.out.println("HttpDownload valid fail!");
						break;
					}
				}
				os.write(buf, 0, len);
				size += len;
			}
/*			if(url.contains("jdbstatic")) {
				//cloudflare
				try {
					Thread.sleep(57L);
				} catch (InterruptedException e1) {
				}
				WebRequest wr2 = new WebRequest(new URL("https://c0.jdbstatic.com/favicon.ico"));
				wr2.setAdditionalHeader("Accept", HttpAccepts.image.name());
				for(Entry<String, String> e:headers.entrySet()) {
					wr2.setAdditionalHeader(e.getKey(), e.getValue());
				}
				wr2.setAdditionalHeader("Referer", url);
				this.genWc().getPage(wr2);
//				System.out.println("cloud flare addintion:"+url);
			}*/
			return size;
		} catch (IOException | FailingHttpStatusCodeException e) {
			throw new WebsiteConnectException(e);
		} finally {
			try {
				if(is != null) {
					is.close();
				}
			} catch(Exception e) {}
			try {
				if(os != null) {
					os.close();
				}
			} catch(Exception e) {}
			if(!valid) {
				this.close();
			}
		}
	}
	@Override
	public String getIdent() {
		return "downloader";
	}
}
