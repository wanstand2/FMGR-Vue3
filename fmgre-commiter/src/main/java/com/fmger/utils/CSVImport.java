package com.fmger.utils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import org.apache.poi.ss.formula.functions.T;

public class CSVImport<T> {

	private static final String UTF8_BOM = "\uFEFF";
	public List<T> importCsv(File file, Class<T> clazz, String[] names,
			IConvert[] convs) {
				return importCsv(file, clazz, names, convs, 0);
			}
	public List<T> importCsv(File file, Class<T> clazz, String[] names,
			IConvert[] convs, int jumpLine) {
				try {
		List<T> ls = new ArrayList<T>();
		String[] ss = Filer.getFileContents(file);
		for(String s:ss) {
			if(jumpLine > 0) {
				jumpLine--;
				continue;
			}
			T t = (T) clazz.newInstance();
			if(s.startsWith(UTF8_BOM)) {
				s = s.substring(1);
			}
			String[] ts = s.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
			for(int i=0;i<ts.length;i++) {
				String _s = ts[i];
				if(names.length <= i) continue;
				if(convs.length <= i) continue;
				String nm = names[i];
				Field f = null;
				Class _c = clazz;
				while(!_c.equals(Object.class)) {
					try {
						f = _c.getDeclaredField(nm);
					} catch(Exception e) {}
					_c = _c.getSuperclass();
				}
				if(f == null) {
					throw new RuntimeException("Feild " + nm + " not found in class " + clazz.getSimpleName());
				}
				f.setAccessible(true);
				Class c = f.getType();
				Object o = null;
				if(_s == null || _s.trim().length() == 0) {
					f.set(t, null);
					continue;
				}
				_s = _s.trim();
				if(convs != null && convs.length > i && convs[i] != null) {
					o = convs[i].conv(_s);
				} else if(String.class.equals(c)) {
					o = _s.trim();
				} else if(Long.class.equals(c)) {
					o = Long.parseLong(_s.trim());
				} else if(BigDecimal.class.equals(c)) {
					if(_s.startsWith("(") && _s.endsWith(")")) {
						//负数的情况
						_s = '-' + _s.substring(1, _s.length()-1);
					}
					o = new BigDecimal(_s.trim());
				} else if(Date.class.equals(c)) {
					o = Date.parse(_s);
				} else {
					throw new RuntimeException("无法处理的类型"+c.getName());
				}
				f.set(t, o);
//				System.out.println(nm + ":" + o);
			}
			ls.add(t);
			System.out.println(t);
		}
		return ls;
	} catch (Exception e) {
		throw new RuntimeException(e);
	}
	}
}
