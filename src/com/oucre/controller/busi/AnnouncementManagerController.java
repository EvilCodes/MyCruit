package com.oucre.controller.busi;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oucre.core.mode.json.AjaxJson;
import com.oucre.core.mode.search.EasyUiPager;
import com.oucre.core.util.ValidateUtil;
import com.oucre.pojo.Announcement;
import com.oucre.service.AnnouncementManagerService;

@Controller
@RequestMapping(value = "/busi")
public class AnnouncementManagerController {
	@Resource
	private AnnouncementManagerService announcementManagerService;

	@RequestMapping(value = "/AnnouncementManager.do")
	public @ResponseBody
	Map<String, Object> findAnnouncementSearch(@RequestParam(value = "rows") Integer rows, @RequestParam(value = "page") Integer page, @RequestParam(value = "sort") String sort, @RequestParam(value = "order") String order, HttpServletRequest req) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (ValidateUtil.neNull(req.getParameter("title"))) {
			map.put("title", req.getParameter("title"));
		}
		return announcementManagerService.findAnnouncementSearch(map, new EasyUiPager(rows, page, sort, order));
	}

	@RequestMapping(value = "/AnnouncementManagerAdd.do")
	public @ResponseBody
	AjaxJson aadAnnouncementManager(@ModelAttribute Announcement entity) {
		return announcementManagerService.addAnnouncementManager(entity);
	}

	@RequestMapping(value = "/AnnouncementManagerUpd.do")
	public @ResponseBody
	AjaxJson updAnnouncementManager(@ModelAttribute Announcement entity) {
		return announcementManagerService.updAnnouncementManager(entity);
	}

	@RequestMapping(value = "/AnnouncementManagerDel.do")
	public @ResponseBody
	AjaxJson delAnnouncementManager(HttpServletRequest req) {
		return announcementManagerService.delAnnouncementManager(req.getParameter("id"));
	}


}
