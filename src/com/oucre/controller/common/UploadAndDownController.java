package com.oucre.controller.common;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.oucre.core.mode.json.AjaxJson;
import com.oucre.core.util.FilePubUtils;

@Controller
@RequestMapping(value = "/common")
public class UploadAndDownController {

	@RequestMapping(value = "/upload.do")
	public @ResponseBody
	AjaxJson upload(HttpServletResponse response, HttpServletRequest request, @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
		AjaxJson a = FilePubUtils.uploadMultipartFile(file, "temp/img", null, 2);
		return a;

	}

	@RequestMapping(value = "/downloadimg.do")
	public void downloadImg(HttpServletRequest req, HttpServletResponse res, @RequestParam(value = "filename") String filename, @RequestParam(value = "path") String path) throws Exception {
		FilePubUtils.downloadImg(path +"/"+ filename, res);
	}
}
