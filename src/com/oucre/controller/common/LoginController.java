package com.oucre.controller.common;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oucre.core.mode.json.AjaxJson;
import com.oucre.core.util.EncryptUtil;
import com.oucre.core.util.ValidateUtil;
import com.oucre.service.LoginService;

@Controller
@RequestMapping(value = "/common")
public class LoginController {
	@Resource
	private LoginService userService;

	@RequestMapping(value = "/login.do", method = RequestMethod.POST, params = "method=alogin")
	public @ResponseBody
	AjaxJson alogin(@RequestParam(value = "name") String name, @RequestParam(value = "password") String password, @RequestParam(value = "code") String code, HttpServletRequest req, HttpServletResponse res) {
		if (ValidateUtil.isNull(code)) {
			return new AjaxJson(false, "验证码不能为空");
		}
		if (ValidateUtil.isNull(name)) {
			return new AjaxJson(false, "账户不能为空");
		}
		if (ValidateUtil.isNull(password)) {
			return new AjaxJson(false, "密码不能为空");
		}
		if (code.equalsIgnoreCase(req.getSession().getAttribute("imageCode").toString())) {
			Map<String, Object> map = userService.loginUser(name, EncryptUtil.getMD5(password));
			if (map != null && map.size() > 0) {
				if (map.get("success").equals("Y")) {
					req.getSession().setAttribute("user_info", map.get("user"));
					req.getSession().setAttribute("role_info", map.get("role"));
					req.getSession().setAttribute("enterprise_info", map.get("enterprise"));
					return new AjaxJson(true);
				}
				return new AjaxJson(false, map.get("msg").toString());
			}
			return new AjaxJson(false);
		} else {
			return new AjaxJson(false, "验证码填写有误!");
		}
	}

	@RequestMapping(value = "/login.do", method = RequestMethod.POST, params = "method=clogin")
	public @ResponseBody
	AjaxJson clogin(@RequestParam(value = "name") String name, @RequestParam(value = "password") String password,HttpServletRequest req) {
		if (ValidateUtil.isNull(name)) {
			return new AjaxJson(false, "账户不能为空");
		}
		if (ValidateUtil.isNull(password)) {
			return new AjaxJson(false, "密码不能为空");
		}
		Map<String, Object> map = userService.loginUser(name, password);
		if (map != null && map.size() > 0) {
			if (map.get("success").equals("Y")) {
				Map<String, Object> info = new HashMap<String, Object>();
				// session中存放内容，使客户端能保持连接
				req.getSession().setAttribute("user_info", map.get("user"));
//				req.getSession().setAttribute("role_info", map.get("role"));
//				req.getSession().setAttribute("enterprise_info", map.get("enterprise"));
				
				info.put("user_info", map.get("user"));
				info.put("user_enterprise", map.get("enterprise"));
				return new AjaxJson(true, map.get("msg").toString(), info);// 登录成功返回的json
			}
			return new AjaxJson(false, map.get("msg").toString());// 错误信息反馈 比如
		}
		return new AjaxJson(false, "出现错误");
	}

	@RequestMapping(value = "/quit.do")
	public String quit(HttpServletRequest req) {
		req.getSession().removeAttribute("user_info");
		req.getSession().removeAttribute("role_info");
		req.getSession().removeAttribute("enterprise_info");
		return "redirect:/login.jsp";
	}
}
