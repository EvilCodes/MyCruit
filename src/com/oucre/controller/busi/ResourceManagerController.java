package com.oucre.controller.busi;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oucre.core.mode.json.AjaxJson;
import com.oucre.core.mode.search.EasyUiPager;
import com.oucre.core.util.ValidateUtil;
import com.oucre.pojo.Resource;
import com.oucre.service.ResourceManagerService;

@Controller
@RequestMapping(value = "/busi")
public class ResourceManagerController {

	@javax.annotation.Resource
	private ResourceManagerService resourceManagerService;

	@RequestMapping(value = "/ResourceManager.do")
	public @ResponseBody
	Map<String, Object> findResourceSearch(
			@RequestParam(value = "rows") Integer rows,
			@RequestParam(value = "page") Integer page,
			@RequestParam(value = "sort") String sort,
			@RequestParam(value = "order") String order, HttpServletRequest req) {
		Map<String, Object> map = new HashMap<String, Object>();
		String name = req.getParameter("name");
		String code = req.getParameter("code");
		if (ValidateUtil.neNull(name)) {
			map.put("name", name);
		}
		if (ValidateUtil.neNull(code)) {
			map.put("code", code);
		}
		return resourceManagerService.findResourceSearch(map, new EasyUiPager(
				rows, page, sort, order));
	}

	@RequestMapping(value = "/ResourceManagerUpd.do")
	public @ResponseBody
	AjaxJson updResource(@ModelAttribute Resource resource) {
		return resourceManagerService.updResource(resource);
	}

	@RequestMapping(value = "/ResourceManagerAdd.do")
	public @ResponseBody
	AjaxJson addResource(@ModelAttribute Resource resource) {
		return resourceManagerService.addResource(resource);
	}

	@RequestMapping(value = "/ResourceManagerDel.do")
	public @ResponseBody
	AjaxJson delRole(HttpServletRequest req) {
		return resourceManagerService.delResource(req.getParameter("id"));
	}
}
