package com.oucre.controller.busi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.oucre.core.mode.json.AjaxJson;
import com.oucre.core.mode.search.EasyUiPager;
import com.oucre.core.mode.tree.TreeNode;
import com.oucre.core.util.ValidateUtil;
import com.oucre.pojo.Role;
import com.oucre.service.RoleManagerSerivce;

@Controller
@RequestMapping(value = "/busi/")
public class RoleManagerController {

	@Resource
	private RoleManagerSerivce roleControlSerivce;

	@RequestMapping(value = "/RoleManager.do")
	public @ResponseBody
	Map<String, Object> findRoleSearch(@RequestParam(value = "rows") Integer rows, @RequestParam(value = "page") Integer page, @RequestParam(value = "sort") String sort, @RequestParam(value = "order") String order, HttpServletRequest req) {
		Map<String, Object> map = new HashMap<String, Object>();
		String name = req.getParameter("name");
		if (ValidateUtil.neNull(name)) {
			map.put("name", name);
		}
		return roleControlSerivce.findRoleSearch(map, new EasyUiPager(rows, page, sort, order));
	}

	@RequestMapping(value = "/RoleManagerUpd.do")
	public @ResponseBody
	AjaxJson updRole(@ModelAttribute Role role) {
		return roleControlSerivce.updRole(role);
	}

	@RequestMapping(value = "/RoleManagerAdd.do")
	public @ResponseBody
	AjaxJson addRole(@ModelAttribute Role role) {
		return roleControlSerivce.addRole(role);
	}

	@RequestMapping(value = "/RoleManagerDel.do")
	public @ResponseBody
	AjaxJson delRole(HttpServletRequest req) {
		return roleControlSerivce.delRole(req.getParameter("id"));
	}

	@RequestMapping(value = "/RoleManagerUpdResources.do", params = "method=getRoleResource")
	public @ResponseBody
	List<TreeNode> getRoleResource(HttpServletRequest req) {
		return roleControlSerivce.getRoleResource(Integer.parseInt(req.getParameter("id")));
	}

	@RequestMapping(value = "/RoleManagerUpdResources.do", params = "method=updRoleResource")
	public @ResponseBody
	AjaxJson updRoleResource(HttpServletRequest req) {
		Gson gson = new Gson();
		Integer id = Integer.parseInt(req.getParameter("id"));
		List<TreeNode> checked = gson.fromJson(req.getParameter("checked"), new TypeToken<List<TreeNode>>() {
		}.getType());
		List<TreeNode> unchecked = gson.fromJson(req.getParameter("unchecked"), new TypeToken<List<TreeNode>>() {
		}.getType());
		List<TreeNode> indeterminate = gson.fromJson(req.getParameter("indeterminate"), new TypeToken<List<TreeNode>>() {
		}.getType());
		return roleControlSerivce.updRoleResource(checked, unchecked, indeterminate, id);
	}

}
