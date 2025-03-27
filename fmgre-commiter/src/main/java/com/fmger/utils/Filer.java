package com.fmger.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Filer {

	public static String getExt(File f) {
		String name = f.getName();
		if(name.contains("."))
			return name.substring(name.lastIndexOf(".")+1);
		return "";
	}
	public static String getName(File f) {
		String name = f.getName();
		if(name.contains(".")) {
			return name.substring(0, name.lastIndexOf("."));
		}
		return name;
	}
	public static String getFolder(File f) {
		File folder = f.getParentFile();
		return getName(folder);
	}
	public static byte[] getFileBytes(File f) throws IOException {
		if (!f.exists())
			return null;
		FileInputStream is = null;
		byte[] ret = new byte[(int) f.length()];
		try {
			is = new FileInputStream(f);
			is.read(ret);
			return ret;
		} finally {
			try {
			if (is != null)
				is.close();
			} catch (IOException e) {
			}
		}
	}
	public static byte[] getFileBytes(String fname) throws IOException {
		return getFileBytes(new File(fname));
	}
	public static void writeFileContents(File f, List<String> contents) throws IOException {
		StringBuilder sb = new StringBuilder();
		for(String content:contents) {
			sb.append(content).append("\r\n");
		}
		if(f.exists()) {
			f.delete();
		}
		writeFileBytes(f, sb.toString().getBytes());
	}
	public static void writeFileContents(File f, String[] contents) throws IOException {
		StringBuilder sb = new StringBuilder();
		for(String content:contents) {
			sb.append(content).append("\r\n");
		}
		if(f.exists()) {
			f.delete();
		}
		writeFileBytes(f, sb.toString().getBytes());
	}
	public static void writeFileBytes(File f, byte[] bytes) throws IOException {
		OutputStream os = new FileOutputStream(f); 
		try {
			os.write(bytes);
		} finally {
		try {
			if(os != null)
			os.close();
		} catch (IOException e) {
		}
		}
	}
	public static String getFileContent(File f) throws IOException {
		if (!f.exists())
			return "";
		InputStreamReader read = null;
		BufferedReader bufferedReader = null;
		String ret = "";
		try {
			read = new InputStreamReader(new FileInputStream(f), "utf-8");
			bufferedReader = new BufferedReader(read);
			String lineTxt = null;
			while ((lineTxt = bufferedReader.readLine()) != null) {
				ret += lineTxt;
			}
		} finally {
			try {
				if (bufferedReader != null)
					bufferedReader.close();
			} catch (IOException e) {
			}
			try {
				if (read != null)
					read.close();
			} catch (IOException e) {
			}
		}
		return ret;
	}

	public static String[] getFileContents(File f) throws IOException {
		return getFileContents(f, "utf-8");
	}
	public static String[] getFileContents(File f, String encoding) throws IOException {
		if (!f.exists())
			return null;
		InputStreamReader read = null;
		BufferedReader bufferedReader = null;
		List<String> ret = new ArrayList<String>();
		try {
			read = new InputStreamReader(new FileInputStream(f), encoding);
			bufferedReader = new BufferedReader(read);
			String lineTxt = null;
			while ((lineTxt = bufferedReader.readLine()) != null) {
				ret.add(lineTxt);
			}
		} finally {
			try {
				if (bufferedReader != null)
					bufferedReader.close();
			} catch (IOException e) {
			}
			try {
				if (read != null)
					read.close();
			} catch (IOException e) {
			}
		}
		return ret.toArray(new String[ret.size()]);
	}

	public static boolean setFileContent(File f, String content) throws IOException {
		FileWriter fw = null;
		fw = new FileWriter(f);
		fw.write(content);
		fw.close();
		return true;
	}
	
	public static boolean delete(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			for (int i=0; i<children.length; i++) {
				boolean success = delete(new File(dir, children[i]));
				if (!success) {
					return false;
					}
				}
			}
		return dir.delete();
	}
	/*
	public static boolean delete(String dir) {
		return delete(new File(dir));
	}
	public static void move(File src, File dst) {
		if(!src.exists()) throw new RuntimeException("移动源文件不存在："+src.getPath());
		if(dst.exists() && !dst.isDirectory()) throw new RuntimeException("移动目标文件存在："+dst.getPath());
		if(src.isDirectory() != dst.isDirectory()) 
			throw new RuntimeException("移动源文件和目标文件不匹配："+src.getPath() + "<->"+ dst.getPath());
		if(src.isDirectory()) {
			String[] children = src.list();
			for (int i=0; i<children.length; i++) {
				File s2 = new File(children[i]);
				File d2 = new File(dst.getPath() + File.separator + s2.getName());
				move(s2, d2);
			}
		} else {
			File
		}
		
	}
	*/
	public static void copy(File from, File to) {
		copy(from, to, false);
	}
	public static void copy(File from, File to, boolean silent) {
		long tm = System.currentTimeMillis();
		if(!to.getParentFile().exists()) {
			to.getParentFile().mkdirs();
		}
		try {
			if(from.isFile()) {
				FileUtils.copyFile(from, to);
			} else {
				FileUtils.copyDirectory(from, to);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tm = (System.currentTimeMillis() - tm)/1000L;
		long size = FileUtils.sizeOf(to);
		if(!silent) {
			logs("FILE-COPY: %s-->%s, speed = %.2fM/s", from.getPath(), to.getPath(), (double)size/(double)tm/1000000D);
		}
		
	}
	public static void move(File from, File to) {
		move(from, to, false);
	}
	private static void move(File from, File to, boolean silent) {
		if(to.getPath().equals(from.getPath())) {
			//相同无需处理
			if(!silent) {
				logs("FILE-NoNeed-MOVE: %s", from.getPath());
			}
			return;
		}
		if(to.getPath().equalsIgnoreCase(from.getPath())) {
			File tmp = new File(to.getPath() + "_tmp.exchange");
			move(from, tmp, true);
			move(tmp, to, true);
		} else if(to.exists()) {
			throw new RuntimeException("目标文件["+to.getPath()+"]存在，不能用源文件["+from.getPath()+"]覆盖！");
		} else {
			if(!to.getParentFile().exists()) {
				to.getParentFile().mkdirs();
			}
			if(!from.renameTo(to)) {
				try {
					if(from.isFile()) {
						FileUtils.moveFile(from, to);
					} else {
						FileUtils.moveDirectory(from, to);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				Thread.sleep(349L);
			} catch (InterruptedException e) {
			}
		}
		if(!silent) {
			logs("FILE-MOVE: %s-->%s", from.getPath(), to.getPath());
		}
	}
	
	public static interface IFolderSizeFilter {
		public boolean cntIt(File f);
	};
	
	public static long folderSize(File f, IFolderSizeFilter cnt) {
		if(!cnt.cntIt(f)) {
			return 0L;
		}
		if(f.isFile()) return f.length();
		File[] fs = f.listFiles();
		long ret = 0L;
		for(File _f:fs) {
			ret += folderSize(_f, cnt);
		}
		return ret;
	}
	
	static Logger log = LoggerFactory.getLogger(Filer.class);
	public static void logs(String format, Object...objs) {
		log.debug(String.format(format, objs));
		System.out.println(String.format(format, objs));
	}
	
}
