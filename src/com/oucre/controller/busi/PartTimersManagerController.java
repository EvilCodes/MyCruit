package com.oucre.controller.busi;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oucre.core.mode.json.AjaxJson;
import com.oucre.core.mode.search.EasyUiPager;
import com.oucre.core.util.ValidateUtil;
import com.oucre.pojo.User;
import com.oucre.service.PartTimersManagerService;

@Controller
@RequestMapping(value = "/busi")
public class PartTimersManagerController {

	@Resource
	private PartTimersManagerService partTimersManagerService;

	@RequestMapping(value = "/PartTimersManager")
	public @ResponseBody
	Map<String, Object> findPartTimersSearch(@ModelAttribute EasyUiPager easyUiPager, HttpServletRequest req) {
		Map<String, Object> map = new HashMap<String, Object>();
		String keyword = req.getParameter("keyword");
		if (ValidateUtil.neNull(keyword)) {
			map.put("keyword", keyword);
		}
		return partTimersManagerService.findPartTimersSearch(map, easyUiPager, req.getSession().getAttribute("user_info"));
	}

	@RequestMapping(value = "/PartTimersManagerAdd")
	public @ResponseBody
	AjaxJson addPartTimers(@ModelAttribute User user, HttpSession session) {
		return partTimersManagerService.addPartTimers(user, session.getAttribute("user_info"));
	}

	@RequestMapping(value = "/PartTimersManagerUpd")
	public @ResponseBody
	AjaxJson updPartTimers(@ModelAttribute User user, HttpSession session) {
		return partTimersManagerService.updPartTimers(user, session.getAttribute("user_info"));
	}

	@RequestMapping(value = "/PartTimersManagerDel")
	public @ResponseBody
	AjaxJson delPartTimers(@RequestParam(value = "id") Integer id, HttpSession session) {
		return partTimersManagerService.delPartTimers(id, session.getAttribute("user_info"));
	}

	@RequestMapping(value = "/PartTimersManagerUpdPassword")
	public @ResponseBody
	AjaxJson updPartTimersPassword(@ModelAttribute User user, HttpSession session) {
		return partTimersManagerService.updPartTimersPassword(user, session.getAttribute("user_info"));
	}

	@RequestMapping(value = "/PartTimersManagerUpdStatus")
	public @ResponseBody
	AjaxJson updPartTimersStatus(@ModelAttribute User user, HttpSession session) {
		return partTimersManagerService.updPartTimersStatus(user, session.getAttribute("user_info"));
	}

}
