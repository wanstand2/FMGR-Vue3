package com.fmger.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Locale;

public class ParserCollection {

	private static SimpleDateFormat[] dfs = new SimpleDateFormat[] {
			new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSSZ"),
			new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"),
			new SimpleDateFormat("yyyy-MM-dd"),
			new SimpleDateFormat("yyyy-MM-dd HH:mm"),
			new SimpleDateFormat("MMM dd, yyyy h:mm:ss aa", Locale.ENGLISH)
	};
	
	public static Long parseDate(String s) {
		Long ret = null;
		if(s == null) return null;
		for(SimpleDateFormat df:dfs) {
			try {
				ret = df.parse(s.trim()).getTime();
				break;
			} catch (ParseException e) {
			}
		}
		return ret;
	}

	public static Long parseLenth(String s) {
		String[] ss = s.split(":");
		long l = 0L;
		for(int i=0;i<ss.length;i++) {
			l = l*60L + Long.parseLong(ss[i]);
		}
		l = l*1000L;
		return l;
	}

	public static final long KB = 1000L;
	public static final long MB = 1000L*KB;
	public static final long GB = 1000L*MB;
	public static int pointSize(double d) {
		if(d < 10D) return 2;
		if(d < 100D) return 1;
		return 0;
	}
	public static String size(long size) {
		Double d;
		String b;
		if(size > GB) {
			d = (double)size/(double)GB;
			b = "GB";
		} else if(size > MB) {
			d = (double)size/(double)MB;
			b = "MB";
		} else if(size > KB) {
			d = (double)size/(double)KB;
			b = "KB";
		} else {
			d = (double)size;
			b = "B ";
		}
		int ps = pointSize(d);
		return String.format((ps == 0 ? " " : "") + "%." + ps + "f%s" , d, b);
	}
	
	public static long parseSize(String size) {
		long l = 0;
		if(size == null || size.trim().length() == 0) return 0L;
		if(size.contains("GB")) {
			l = GB;
			size = size.substring(0, size.indexOf("GB")).trim();
		} else if(size.contains("GiB")) {
			l = GB;
			size = size.substring(0, size.indexOf("GiB")).trim();
		} else if(size.contains("MB")) {
			l = MB;
			size = size.substring(0, size.indexOf("MB")).trim();
		} else if(size.contains("MiB")) {
			l = MB;
			size = size.substring(0, size.indexOf("MiB")).trim();
		} else if(size.contains("KB")) {
			l = KB;
			size = size.substring(0, size.indexOf("KB")).trim();
		} else if(size.contains("bytes")) {
			l = 1L;
			size = size.substring(0, size.indexOf("bytes")).trim();
		} else if(size.contains("Bytes")) {
			l = 1L;
			size = size.substring(0, size.indexOf("Bytes")).trim();
		}
		double d = 0D;
		try {
			d = Double.parseDouble(size);
		} catch(NumberFormatException e) {
			return 0L;
		}
		return (long)(d*l);
	}
	

	public static final int CM = 10;
	public static final int MM = 1;
	public static int parseLength(String length) {
		int l = 0;
		if(length == null || length.trim().length() == 0) return 0;
		if(length.contains("cm") || length.contains("CM")) {
			l = CM;
			length = length.replace("cm", "").replace("CM", "").trim();
		} else if(length.contains("mm") || length.contains("MM")) {
			l = MM;
			length = length.replace("mm", "").replace("MM", "").trim();
		}
		double d = 0D;
		try {
			d = Double.parseDouble(length);
		} catch(NumberFormatException e) {
			return 0;
		}
		return (int)(d*l);
	}
	
	public static String parseFileExt(String name) {
		if(name == null) return null;
		if(name.contains(".")) {
			return name.substring(name.lastIndexOf(".")+1);
		}
		return "";
	}
	
	public static String parseFileName(String name) {
		if(name == null) return null;
		if(name.contains(".")) {
			return name.substring(0, name.lastIndexOf("."));
		}
		return name;
	}
	
	public static String[] videoExts = new String[] {
			"avi",
			"mp4",
			"rmvb",
			"mkv",
			"wmv",
			"iso",
			"mpg",
			"ASF",
			"rm",
			"m4v"
	};
	static {
		Arrays.sort(videoExts);
	}
	public static boolean isVideo(String ext) {
		if(ext == null || ext.length() == 0) return false;
		return Arrays.binarySearch(ParserCollection.videoExts, ext.toLowerCase()) >= 0;
	}

	public static String[] imageExts = new String[] {
			"jpg",
			"jpeg",
			"gif",
			"png",
			"bmp"
	};
	static {
		Arrays.sort(imageExts);
	}
	public static boolean isImage(String ext) {
		if(ext == null || ext.length() == 0) return false;
		return Arrays.binarySearch(ParserCollection.imageExts, ext.toLowerCase()) >= 0;
	}
	
}
