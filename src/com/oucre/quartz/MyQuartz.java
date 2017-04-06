package com.oucre.quartz;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.oucre.pojo.Student;
import com.oucre.service.StudentManagerService;

public class MyQuartz {
	private Logger logger = Logger.getLogger(this.getClass());
	@Resource
	private StudentManagerService studentManagerService;

	public void clearimgtemp() {
		try {
			List<Student> list = studentManagerService.findAllStudents();
			for (Student s : list) {
				// 审核通过状态 并且是 自己转化 时，开启转化时间倒计时
				if ("2".equals(s.getStatus()) && "1".equals(s.getConver())) {
					int days = s.getDays() - 1;
					if (days == 0) {
						s.setConver("0");// 置为人人都可以转化
					}
					s.setDays(days);
					studentManagerService.updStudent(s,null);
				}
			}
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
	}
}
