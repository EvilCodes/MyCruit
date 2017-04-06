package com.oucre.core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

import javax.activation.FileDataSource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.oucre.core.mode.json.AjaxJson;

/**
 * 文件 公共方法 包含文件的上传、下载等操作
 * 
 */
public class FilePubUtils {
	/**
	 * 数据缓冲区
	 */
	public final static byte[] buffer = new byte[1024];

	private static String DiskPath = "E://file/";

	/**
	 * 文件上传
	 * 
	 * @param file
	 * @param path
	 * @param name
	 * @param size
	 */

	public static AjaxJson uploadMultipartFile(MultipartFile file, String path,
			String name, Integer size) {
		try {
			if (file.isEmpty()) {
				return new AjaxJson(false, "文件为空");
			}
			if (file.getSize() >= 1024 * (size * 1024)) {
				return new AjaxJson(false, "文件大于" + size + "Mb");
			}
			String tname = "";
			String rname = file.getOriginalFilename().substring(
					file.getOriginalFilename().lastIndexOf("."),
					file.getOriginalFilename().length());
			if (name != null && name.length() > 0) {
				tname = name + rname;
			} else {
				tname = DateUtil.toString(new java.util.Date(),
						"yyyy-MM-dd_hh-mm-ss")
						+ "$"
						+ UUID.randomUUID().toString() + rname;
			}
			path = path + "/"+ DateUtil.toString(new java.util.Date(), "yyyy-MM-dd");
			File f = new File(DiskPath + path, tname);
			if (!f.exists()) {
				f.mkdirs();
			}
			file.transferTo(f);
			String[] str = new String[2];
			str[0] = path;
			str[1] = tname;
			return new AjaxJson(true, "操作成功", str);
		} catch (Exception e) {
			e.printStackTrace();
			return new AjaxJson(false, "出现异常");
		}
	}

	/**
	 * 删除文件
	 * 
	 * @param src
	 * @return
	 */
	public static boolean del(File src) {
		try {
			if (src.exists()) {
				src.delete();
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 剪切方法
	 * 
	 * @param src
	 * @param dest
	 * @return
	 */
	public static boolean cut(File src, File dest) {
		try {
			if (copyFile(src, dest)) {
				del(src);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	/**
	 * 拷贝文件并且创建文件夹
	 * 
	 * @param src
	 * @param dest
	 * @return
	 */
	public static boolean copyFile(File src, File dest) {
		if (!dest.getParentFile().exists()) {
			dest.getParentFile().mkdirs();
		}
		return copy(src, dest);
	}

	/***
	 * 拷贝文件
	 * 
	 * @param in
	 * @param out
	 * @return
	 */
	public static boolean copy(File in, File out) {
		try {
			FileInputStream fis = new FileInputStream(in);
			FileOutputStream fos = new FileOutputStream(out);
			byte[] buf = new byte[1024];
			int i = 0;
			while ((i = fis.read(buf)) != -1) {
				fos.write(buf, 0, i);
			}
			fis.close();
			fos.close();
			return true;
		} catch (IOException ie) {
			ie.printStackTrace();
			return false;
		}
	}

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
		System.out.println(file.getName());
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
		path = DiskPath + path;
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
		response.setContentType("APPLICATION/OCTET-STREAM");
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

	public static void downloadImg(String path, HttpServletResponse response) {
		path = DiskPath + path;
		try {
			File f = new File(path);
			if (!f.exists()) {
				// this.dispatcher(request , response , "/NoFound.jsp");
				return;
			}
			InputStream in = new FileInputStream(path);
			// 设置输出的类型
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.setContentType("image/jpeg");
			ServletOutputStream out = null;
			try {
				out = response.getOutputStream();
				byte[] data = new byte[1024];
				int length = -1;
				while ((length = in.read(data)) != -1) {
					out.write(data, 0, length);
				}
				out.flush();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				if (out != null) {
					try {
						out.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * public void buildPdfDocument(Map model, Document document, PdfWriter
	 * writer, HttpServletRequest request, HttpServletResponse response) throws
	 * Exception {
	 * 
	 * String excelName = "用户信息.pdf"; //
	 * 设置response方式,使执行此controller时候自动出现下载页面,而非直接使用excel打开
	 * response.setContentType("APPLICATION/OCTET-STREAM");
	 * response.setHeader("Content-Disposition", "attachment; filename="+
	 * URLEncoder.encode(excelName, "UTF-8"));
	 * 
	 * // List stuList = (List) model.get("list"); //显示中文 BaseFont bfChinese =
	 * BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",
	 * BaseFont.NOT_EMBEDDED); com.lowagie.text.Font FontChinese = new
	 * com.lowagie.text.Font(bfChinese, 12, com.lowagie.text.Font.NORMAL );
	 * 
	 * String value = null; for (int i = 0; i < stuList.size(); i++) { Student s
	 * = (Student)stuList.get(i); value = "姓名: "+ s.getName()+",性别: "+s.getSex()
	 * + ",日期: " + s.getDate() + ",总数: " + s.getCount(); document.add(new
	 * Paragraph(value,FontChinese)); }
	 */

}
