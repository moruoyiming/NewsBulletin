package com.mrym.newsbulletion.utils.file;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

/**
 * Created by Jian on 2016/8/25.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public class FileHelper {

	private static String TAG = "FileHelper";

	/**
	 * 判断文件是否存在
	 * 
	 * @param filePath
	 *            文件路径
	 * @return
	 */
	public static boolean fileIsExist(String filePath) {
		if (filePath == null || filePath.length() < 1) {
			Log.e(TAG, "param invalid, filePath: " + filePath);
			return false;
		}

		File f = new File(filePath);
		if (!f.exists()) {
			return false;
		}
		return true;
	}

	/**
	 * 判断文件夹是否存在
	 * 
	 * @param folderPath
	 *            文件夹路径
	 * @return
	 */
	public static boolean folderIsExist(String folderPath) {
		if (folderPath == null || folderPath.length() < 1) {
			Log.e(TAG, "param invalid, filePath: " + folderPath);
			return false;
		}

		File f = new File(folderPath);
		if (!f.exists() && !f.isDirectory()) {
			return false;
		}
		return true;
	}

	public static InputStream readFile(String filePath) {
		if (null == filePath) {
			// log.e("Invalid param. filePath: " + filePath);
			return null;
		}

		InputStream is = null;

		try {
			if (fileIsExist(filePath)) {
				File f = new File(filePath);
				is = new FileInputStream(f);
			} else {
				return null;
			}
		} catch (Exception ex) {
			// log.e("Exception, ex: " + ex.toString());
			return null;
		}
		return is;
	}

	/**
	 * 创建目录
	 * 
	 * @param filePath
	 * @return
	 */
	public static boolean createDirectory(String filePath) {
		if (null == filePath) {
			return false;
		}

		File file = new File(filePath);

		if (file.exists()) {
			return true;
		}

		file.mkdirs();
		return true;
	}

	/**
	 * 递归建立文件里所有文件的文件夹
	 *
	 * data路径：/data/data/files
	 * @throws IOException
	 */
	public static void copyFileOrDir(Context context, String path, String topath) throws IOException {
		createDirectory(topath);
		AssetManager assetManager = context.getAssets();
		String assets[] = null;
		assets = assetManager.list(path);
		if (assets.length == 0) {
			copyFile(context, path, topath);
		} else {
			String fullPath = topath + "/" + path;
			File dir = new File(fullPath);
			if (!dir.exists())
				dir.mkdir();
			for (int i = 0; i < assets.length; ++i) {
				copyFileOrDir(context, path + "/" + assets[i], topath);
			}
		}
	}

	public static void copyFile(Context context, String filename, String topath) {
		AssetManager assetManager = context.getAssets();
		InputStream in = null;
		OutputStream out = null;
		try {
			in = assetManager.open(filename);
			String newFileName = topath + "/" + filename;
			out = new FileOutputStream(newFileName);
			byte[] buffer = new byte[1024];
			int read;
			while ((read = in.read(buffer)) != -1) {
				out.write(buffer, 0, read);
			}
			in.close();
			in = null;
			out.flush();
			out.close();
			out = null;
		} catch (Exception e) {
			Log.e("tag", e.getMessage());
		}
	}

	/**
	 * 解压缩功能. 将zipFile文件解压到folderPath目录下.
	 * 
	 * @throws Exception
	 */
	public static boolean unZipFile(String zipFile, String folderPath) throws ZipException, IOException {

		createDirectory(folderPath);
		ZipFile zfile = new ZipFile(zipFile);
		Enumeration zList = zfile.entries();
		ZipEntry ze = null;
		byte[] buf = new byte[1024];
		while (zList.hasMoreElements()) {
			ze = (ZipEntry) zList.nextElement();
			if (ze.isDirectory()) {
				// Log.d("upZipFile", "ze.getName() = "+ze.getName());
				String dirstr = folderPath + ze.getName();
				// dirstr.trim();
				dirstr = new String(dirstr.getBytes("8859_1"), "GB2312");
				// Log.d("upZipFile", "str = "+dirstr);
				File f = new File(dirstr);
				f.mkdir();
				continue;
			}
			// Log.d("upZipFile", "ze.getName() = "+ze.getName());
			OutputStream os = new BufferedOutputStream(new FileOutputStream(getRealFileName(folderPath, ze.getName())));
			InputStream is = new BufferedInputStream(zfile.getInputStream(ze));
			int readLen = 0;
			while ((readLen = is.read(buf, 0, 1024)) != -1) {
				os.write(buf, 0, readLen);
			}
			is.close();
			os.close();
		}
		zfile.close();
		Log.d("upZipFile", "finishssssssssssssssssssss");
		return true;
	}

	/**
	 * 给定根目录，返回一个相对路径所对应的实际文件名.
	 * 
	 * @param baseDir
	 *            指定根目录
	 * @param absFileName
	 *            相对路径名，来自于ZipEntry中的name
	 * @return java.io.File 实际的文件
	 */
	public static File getRealFileName(String baseDir, String absFileName) {
		String[] dirs = absFileName.split("/");
		File ret = new File(baseDir);
		String substr = null;
		if (dirs.length > 1) {
			for (int i = 0; i < dirs.length - 1; i++) {
				substr = dirs[i];
				try {
					// substr.trim();
					substr = new String(substr.getBytes("8859_1"), "GB2312");

				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ret = new File(ret, substr);

			}
			// Log.d("upZipFile", "1ret = "+ret);
			if (!ret.exists())
				ret.mkdirs();
			substr = dirs[dirs.length - 1];
			try {
				// substr.trim();
				substr = new String(substr.getBytes("8859_1"), "GB2312");
				// Log.d("upZipFile", "substr = "+substr);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			ret = new File(ret, substr);
			// Log.d("upZipFile", "2ret = "+ret);
			return ret;
		}
		return ret;
	}
}

