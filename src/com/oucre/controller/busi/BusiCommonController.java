package com.oucre.controller.busi;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.oucre.core.mode.json.AjaxJson;
import com.oucre.pojo.Role;
import com.oucre.pojo.SendMessage;
import com.oucre.pojo.Student;
import com.oucre.pojo.User;
import com.oucre.service.BusiCommonService;

@Controller
@RequestMapping(value = "/busi/common")
public class BusiCommonController {
	@Resource
	private BusiCommonService commonService;

	/**
	 * 菜单 按钮查询
	 * 
	 * @date 2015年4月1日 上午11:29: 52
	 * @author haoli_jun
	 * @param code
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping(value = "/getButton.do")
	public @ResponseBody
	AjaxJson getButton(@RequestParam(value = "code") Integer code, HttpServletRequest req, HttpServletResponse res) {
		AjaxJson a = new AjaxJson();
		User user = (User) req.getSession().getAttribute("user_info");
		List<com.oucre.pojo.Resource> list = commonService.getButton(user.getRoleid(), code);
		if (list != null && list.size() > 0) {
			a.setSuccess(true);
			a.setMsg("操作成功");
			a.setObj(list);
		}
		return a;
	}

	@RequestMapping(value = "/getMenu.do")
	public ModelAndView getMenu(HttpServletRequest req) {
		ModelAndView m = new ModelAndView();
		User u = (User) req.getSession().getAttribute("user_info");
		if (u.getRoleid() != null) {
			m.setViewName("home/left");
			m.addObject("MENU", commonService.getMenu(u.getRoleid()));
		} else {

		}
		return m;
	}

	@RequestMapping(value = "/UserUpdPassword.do")
	public @ResponseBody
	AjaxJson UserUpdPassword(@RequestParam(value = "passworda") String passworda, @RequestParam(value = "password") String password, HttpServletRequest req) {
		AjaxJson a = new AjaxJson();
		a = commonService.updUserPassword(passworda, password, req.getSession().getAttribute("user_info"));
		if (a.isSuccess()) {
			req.getSession().setAttribute("user_info", (User) a.getObj());
		}
		return a;
	}

	@RequestMapping(value = "/UserUpd.do")
	public @ResponseBody
	AjaxJson UserUpd(@RequestParam(value = "username") String username, HttpServletRequest req) {
		AjaxJson a = new AjaxJson();
		a = commonService.updUser(username, req.getSession().getAttribute("user_info"));
		if (a.isSuccess()) {
			req.getSession().setAttribute("user_info", (User) a.getObj());
		}
		return a;
	}

	@RequestMapping(value = "/findAllRole.do")
	public @ResponseBody
	List<Role> findAllRole() {
		return commonService.findAllRole();
	}

	@RequestMapping(value = "/findAllRoleGroups.do")
	public @ResponseBody
	List<Role> findAllRoleGroups(@RequestParam(value = "groups") String groups) {
		return commonService.findAllRoleGroups(groups);
	}
	
	@RequestMapping(value = "/findAllUsers.do")
	public @ResponseBody
	List<User> findAllUsers() {
		return commonService.findAllUsers();
	}
	
	@RequestMapping(value = "/findStudentByQQ.do")
	public @ResponseBody Student findStudentByQQ(@RequestParam(value = "qq") String qq) {
		return commonService.findStudentByQQ(qq);
	}

	@RequestMapping(value = "/findSendMessageByGnum.do")
	public @ResponseBody SendMessage findSendMessageByGnum(@RequestParam(value = "gnum") String gnum) {
		return commonService.findSendMessageByGnum(gnum);
	}
}
