package com.oucre.core.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.activation.FileDataSource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

/**
 * 文件 公共方法 包含文件的上传、下载等操作
 */
public class FilePubUtils {
	/**
	 * 数据缓冲区
	 */
	public final static byte[] buffer = new byte[1024];

	/**
	 * 单个文件上传
	 * 
	 * @param files
	 *            文件对象
	 * @param path
	 *            上传路径
	 */
	public static void uploadSingleFile(MultipartFile file, String path,
			String name) {
		File mf = new File(path, name);
		try {
			file.transferTo(mf);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @date 2015-3-3 下午5:09:57
	 * @author haoli_jun
	 * @param files
	 * @param path
	 */
	public static String uploadFiles(MultipartFile[] files, String path,
			String name) {
		File mf = null;
		String names = "";
		for (int j = 0; j < files.length; j++) {
			try {
				if (!files[j].isEmpty()) {
					String e = files[j].getOriginalFilename().substring(
							files[j].getOriginalFilename().lastIndexOf("."),
							files[j].getOriginalFilename().length());
					String p = name + "-" + j + e;
					mf = new File(path, p);
					if (!mf.exists()) {
						mf.mkdirs();
					}
					files[j].transferTo(mf);
					names += p + ",";
				}
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		if (names.length() > 5) {
			names = names.substring(0, names.lastIndexOf(","));
		}
		return names;
	}

	/**
	 * 过滤不存在的文件
	 * 
	 * @date 2015-3-3 下午7:12:05
	 * @author haoli_jun
	 * @param files
	 * @return
	 */
	// public File[] filterFile(MultipartFile[] files) {
	// try {
	// MultipartFile[] f = null;
	// for (int i = 0; i < files.length; i++) {
	// if (files[i].isEmpty()) {
	// f = files[i];
	// } else {
	//
	// }
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }

	/**
	 * 获取上传目录下的文件列表
	 * 
	 * @return
	 */
	public static File[] getFileList(String path) {
		File file = new File(path);
		File[] files = file.listFiles();
		return files;
	}

	/**
	 * 文件下载
	 * 
	 * @param req
	 * @param response
	 * @param fileName
	 * @throws UnsupportedEncodingException
	 */
	public static void downloadFile(HttpServletRequest req,
			HttpServletResponse response, String fileName, String path)
			throws UnsupportedEncodingException {
		File file = new File(path, fileName);
		if (!file.exists()) {
			try {
				throw new FileNotFoundException("下载文件不存在");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		FileDataSource fds = new FileDataSource(file);
		response.setContentType(fds.getContentType());
		response.setCharacterEncoding("GBK");
		// 设置下载时显示的名称 bug修改
		if (req.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
			response.setHeader("Content-Disposition", "attachment;"
					+ "filename="
					+ new String(fileName.getBytes("GBK"), "ISO8859-1"));
		} else { // firefox、chrome、safari、opera
			response.setHeader("Content-Disposition", "attachment;filename="
					+ new String(fileName.getBytes("UTF-8"), "ISO8859-1"));
		}
		InputStream is = null;
		OutputStream out = null;
		int read = 0;
		try {
			is = new FileInputStream(file);
			out = response.getOutputStream();
			while ((read = is.read(buffer)) != -1) {
				response.getOutputStream().write(buffer, 0, read);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
