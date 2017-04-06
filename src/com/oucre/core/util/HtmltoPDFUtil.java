package com.oucre.core.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.UUID;

import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.pdf.BaseFont;

public class HtmltoPDFUtil {

	// 支持中文
	public static String htmlToPdf(String name,String path, String context, String css,
			String fontpath, String pdfimg) {
		try {
			File f = new File(path);
			if (!f.exists()) {
				f.mkdirs();
			}
			OutputStream os = new FileOutputStream(path + "/" + name + ".pdf");
			ITextRenderer renderer = new ITextRenderer();
			ITextFontResolver fontResolver = renderer.getFontResolver();
			if (fontpath != null) {
				fontResolver.addFont(fontpath, BaseFont.IDENTITY_H,
						BaseFont.NOT_EMBEDDED);
			} else {
				fontResolver.addFont("C:/Windows/fonts/simsun.ttc",
						BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
			}
			StringBuffer html = new StringBuffer();
			// DOCTYPE 必需写否则类似于 这样的字符解析会出现错误
			html.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
			html.append("<html xmlns=\"http://www.w3.org/1999/xhtml\"><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");
			html.append("<style type='text/css'>body {font-family: SimSun; margin: 0px; padding: 0px;}"+css+"</style></head><body> " + context + " </body></html>");
			renderer.setDocumentFromString(html.toString());
			renderer.setDocumentFromString(html.toString());
			// 解决图片的相对路径问题
			if (pdfimg != null) {
				renderer.getSharedContext().setBaseURL("file:/"+pdfimg);
			}
			renderer.layout();
			renderer.createPDF(os);
			os.close();
			return path + "/" + name + ".pdf";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	

	// 不支持中文
	public static void htmlToPdfus() throws Exception {
		String inputFile = "D:/Test/flying.html";
		String url = new File(inputFile).toURI().toURL().toString();
		String outputFile = "D:/Test/flying.pdf";
		OutputStream os = new FileOutputStream(outputFile);
		ITextRenderer renderer = new ITextRenderer();
		renderer.setDocument(url);
		renderer.layout();
		renderer.createPDF(os);
		os.close();
	}


	public static void htmlToPdf3() throws Exception {
		String inputFile = "F:/TestDemo/test.html";
		String outFile = "F:/TestDemo/test.pdf";
		OutputStream os = null;
		os = new FileOutputStream(outFile);
		ITextRenderer renderer = new ITextRenderer();
		ITextFontResolver fontResolver = renderer.getFontResolver();
		fontResolver.addFont("C:/Windows/fonts/simsun.ttc",
				BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);

		String url = new File(inputFile).toURI().toURL().toString();
		System.out.println("=============url: " + url);
		renderer.setDocument(url);
		renderer.layout();
		renderer.createPDF(os);
		System.out.println("======转换成功!");
		os.close();
	}

	// 支持中文
	public static void htmlToPdf2() throws Exception {
		String outputFile = "F:/TestDemo/demo_3.pdf";
		OutputStream os = new FileOutputStream(outputFile);
		ITextRenderer renderer = new ITextRenderer();
		ITextFontResolver fontResolver = renderer.getFontResolver();
		fontResolver.addFont("C:/Windows/fonts/simsun.ttc",
				BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
		StringBuffer html = new StringBuffer();
		// DOCTYPE 必需写否则类似于 这样的字符解析会出现错误
		html.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
		html.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">")
				.append("<head>")
				.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />")
				.append("<style type=\"text/css\" mce_bogus=\"1\">body {font-family: SimSun;}</style>")
				.append("</head>").append("<body>");
		html.append("<div>支持中文！</div>");
		html.append("</body></html>");
		renderer.setDocumentFromString(html.toString());
		// 解决图片的相对路径问题
		// renderer.getSharedContext().setBaseURL("file:/F:/teste/html/");
		renderer.layout();
		renderer.createPDF(os);
		System.out.println("======转换成功!");
		os.close();
	}

}
