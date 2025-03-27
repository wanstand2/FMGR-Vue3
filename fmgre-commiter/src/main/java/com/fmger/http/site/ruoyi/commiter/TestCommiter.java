package com.fmger.http.site.ruoyi.commiter;

public class TestCommiter extends SiteRuoyiCommiter {

	@Override
	public void commit(String file) {
		this.refreshMaterials();
		System.out.println("Succ");
	}

}
