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
import com.oucre.pojo.Ticket;
import com.oucre.pojo.User;
import com.oucre.service.TrainTicketsManagerService;

@Controller
@RequestMapping(value = "/busi")
public class TrainTicketsManagerController {
	@Resource
	private TrainTicketsManagerService trainTicketsManagerService;
	
	@RequestMapping(value = "/TrainTicketsManager.do")
	public @ResponseBody
	Map<String, Object> findTrainTicketsSearch(@ModelAttribute EasyUiPager easyUiPager, HttpServletRequest req) {
		Map<String, Object> map = new HashMap<String, Object>();
		String tfrom = req.getParameter("tfrom");
		String tto = req.getParameter("tto");
		if (ValidateUtil.neNull(tfrom)) {
			map.put("tfrom", tfrom);
		}
		if (ValidateUtil.neNull(tto)) {
			map.put("tto", tto);
		}
		System.out.println(trainTicketsManagerService.findTrainTicketsSearch(map, easyUiPager,(User)req.getSession().getAttribute("user_info")));
		return trainTicketsManagerService.findTrainTicketsSearch(map, easyUiPager,(User)req.getSession().getAttribute("user_info"));
	}
	
	@RequestMapping(value = "/TrainTicketsManagerAdd.do")
	public @ResponseBody
	AjaxJson addTrainTickets(@ModelAttribute Ticket ticket,HttpSession session) {
		return trainTicketsManagerService.addTrainTickets(ticket,(User)session.getAttribute("user_info"));
	}
	
	@RequestMapping(value = "/TrainTicketsManagerDel.do")
	public @ResponseBody
	AjaxJson delTrainTickets(@RequestParam(value = "id") Integer id, HttpSession session) {
		return trainTicketsManagerService.delTrainTickets(id, (User)session.getAttribute("user_info"));
	}

	/*// ×ª»¯
	@RequestMapping(value = "/StudentManagerUpdConver.do")
	public @ResponseBody
	AjaxJson updStudentConver(@ModelAttribute Student Student,HttpSession session) {
		return studentService.updStudentConver(Student,(User)session.getAttribute("user_info"));
	}
	
	
	// ÉóºË
	@RequestMapping(value = "/StudentManagerUpdStatus.do")
	public @ResponseBody
	AjaxJson updStudentStatus(@ModelAttribute Student Student,HttpSession session) {
		return studentService.updStudentStatus(Student,(User)session.getAttribute("user_info"));
	}

	@RequestMapping(value = "/StudentManagerUpd.do")
	public @ResponseBody
	AjaxJson updStudent(@ModelAttribute Student Student,HttpSession session) {
		return studentService.updStudent(Student,(User)session.getAttribute("user_info"));
	}

	@RequestMapping(value = "/StudentManagerAdd.do")
	public @ResponseBody
	AjaxJson addStudent(@ModelAttribute Student Student,HttpSession session) {
		return studentService.addStudent(Student,(User)session.getAttribute("user_info"));
	}

	@RequestMapping(value = "/StudentManagerDel.do")
	public @ResponseBody
	AjaxJson delStudent(@RequestParam(value = "id") Integer id, HttpSession session) {
		return studentService.delStudent(id, (User)session.getAttribute("user_info"));
	}*/
}
