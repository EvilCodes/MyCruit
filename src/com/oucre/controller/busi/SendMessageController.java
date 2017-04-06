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
import com.oucre.pojo.SendMessage;
import com.oucre.pojo.Student;
import com.oucre.pojo.User;
import com.oucre.service.SendMessageService;

@Controller
@RequestMapping(value = "/busi/")
public class SendMessageController {
	@Resource
	private SendMessageService sendMessageService;
	
	@RequestMapping(value = "/SendMessageManager.do")
	public @ResponseBody
	Map<String, Object> findAnnouncementSearch(@RequestParam(value = "rows") Integer rows, @RequestParam(value = "page") Integer page, @RequestParam(value = "sort") String sort, @RequestParam(value = "order") String order, HttpServletRequest req) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (ValidateUtil.neNull(req.getParameter("gnum"))) {
			map.put("gnum", req.getParameter("gnum"));
		}
		if (ValidateUtil.neNull(req.getParameter("gname"))) {
			map.put("gname", req.getParameter("gname"));
		}
		if (ValidateUtil.neNull(req.getParameter("message"))) {
			map.put("message", req.getParameter("message"));
		}
		return sendMessageService.findSendMessageSearch(map, new EasyUiPager(rows, page, sort, order));
	}
	
	@RequestMapping(value = "/SendMessageManagerAdd.do")
	public @ResponseBody
	AjaxJson addSendMessageManager(@ModelAttribute SendMessage entity,HttpSession session) {
		return sendMessageService.addSendMessage(entity,(User)session.getAttribute("user_info"));
	}
	
	@RequestMapping(value = "/SendMessageManagerUpd.do")
	public @ResponseBody
	AjaxJson updStudent(@ModelAttribute SendMessage entity,HttpSession session) {
		System.out.println("----------------"+entity+"-----------------");
		return sendMessageService.updSendMessage(entity,(User)session.getAttribute("user_info"));
	}
	
	@RequestMapping(value = "/SendMessageManagerDel.do")
	public @ResponseBody
	AjaxJson delStudent(@RequestParam(value = "id") Integer id, HttpSession session) {
		return sendMessageService.delSendMessage(id, (User)session.getAttribute("user_info"));
	}
}
