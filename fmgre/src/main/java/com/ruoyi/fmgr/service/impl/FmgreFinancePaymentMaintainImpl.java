package com.ruoyi.fmgr.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.ruoyi.fmgr.domain.FmgreFinancePayment;
import com.ruoyi.fmgr.service.IFmgreFinancePaymentMaintain;

@Service
public class FmgreFinancePaymentMaintainImpl implements IFmgreFinancePaymentMaintain, Runnable{

	private Thread t = null;
	private Set<FmgreFinancePayment> toAdds = new HashSet<>();
	private List<Long> toMaintains = new ArrayList<Long>();
	
	@Override
	public void run() {
		while(true) {
			
			try {
				Thread.sleep(1L);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
//		this.t = null;
	}
	
	private synchronized void moveIds() {
		
	}

	@Override
	public synchronized void onModify(FmgreFinancePayment payment) {
		if(t == null) {
			t = new Thread(this);
			t.start();
		}
		toAdds.add(payment);
	}

}
