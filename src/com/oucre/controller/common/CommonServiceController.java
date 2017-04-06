package com.oucre.controller.common;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.oucre.pojo.Announcement;
import com.oucre.service.CommonService;

@Controller
@RequestMapping(value = "/common")
public class CommonServiceController {

	@Resource
	private CommonService commonService;

	@RequestMapping(value = "/findAnnouncementTopOne.do")
	public ModelAndView findAnnouncementTopOne() {
		ModelAndView m = new ModelAndView();
		Announcement a = commonService.findAnnouncementTopOne();
		m.addObject("Content", a.getContent());
		m.addObject("Title", a.getTitle());
		m.setViewName("home/news");
		return m;
	}
}
