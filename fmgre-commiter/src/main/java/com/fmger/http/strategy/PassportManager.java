package com.fmger.http.strategy;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fmger.http.ISelfIdent;

public class PassportManager<T extends ISelfIdent> {

	public class Passport {
		private long startTm = System.currentTimeMillis();
		private long tm = 0L;
		private long cnt = 0;
		private String name;
		private long useDur = 0L;
		private long gutter = 100L;
		private T site;
		
		public Passport(T site, String name, long gutter) {
			super();
			this.site = site;
			this.name = name;
			this.gutter = gutter;
		}
		public boolean valid() {
			return System.currentTimeMillis() - this.tm > this.gutter;
		}
		public void inuse() {
			this.tm = System.currentTimeMillis();
			this.cnt++;
		}
		public void outuse() {
			long now = System.currentTimeMillis();
			this.useDur += (now - tm);
			tm = now;
		}
		public long nextTm() {
			return tm + gutter;
		}
		public float useRatio() {
			long now = System.currentTimeMillis();
			double d = (double)useDur*100D/(double)(now-startTm);
			return (float)d;
		}
	}
	private Map<T, Passport> table = new HashMap<T, Passport>();

	public void addPassport(T site,  String name, long gutter) {
		List<Passport> pass = this.getPassList(site.getIdent());
		Passport p = new Passport(site, name, gutter);
		this.table.put(site, p);
		synchronized(passes) {
			pass.add(0, p);
		}
	}
	private Map<String, List<Passport>> passes = new HashMap<String, List<Passport>>();
	private List<Passport> getPassList(String ident) {
		List<Passport> pass = passes.get(ident);
		if(pass == null) {
			synchronized(passes) {
				pass = passes.get(ident);
				if(pass == null) {
					pass = new ArrayList<Passport>();
					passes.put(ident, pass);
				}
			}
		}
		return pass;
	}
	public T getPassport(String ident) {
		List<Passport> pass = getPassList(ident);
		if(pass.size() == 0) return null;
		Passport p = null;
		synchronized(pass) {
			if(pass.size() == 0) return null;
			p = pass.get(0);
			if(p.valid() ) {
				pass.remove(0);
			} else {
				p = null;
			}
		}
		if(p != null) {
			p.inuse();
//			System.out.printf("[%s.%s] INUSE CNT=%03d\n", p.site.getIdent(), p.name, p.cnt);
			return p.site;
		}
		return null;
	}
	public void retPassport(T site) {
		List<Passport> pass = getPassList(site.getIdent());
		Passport p = this.table.get(site);
		p.outuse();
//		System.out.printf("[%s.%s] OUTUSE RATIO=%2.2f\n", p.site.getIdent(), p.name, p.useRatio());
		synchronized(pass) {
			int i=pass.size();
			for(;i>0;i--) {
				Passport _p = pass.get(i-1);
				if(_p.nextTm() < p.nextTm()) {
					break;
				}
			}
			pass.add(i, p);
		}
	}
	
	public int count() {
		return this.table.size();
	}
	
	public void onEnd() {
		synchronized(table) {
			for(Passport p:table.values()) {
				try {
				Method m = p.site.getClass().getMethod("close");
				if(m != null) {
					m.invoke(p.site);
				}} catch(Exception ex) {}
			}
		}
	}
}
