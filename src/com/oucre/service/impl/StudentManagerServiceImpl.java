package com.oucre.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oucre.core.mode.json.AjaxJson;
import com.oucre.core.mode.search.EasyUiPager;
import com.oucre.core.util.Pub;
import com.oucre.core.util.ValidateUtil;
import com.oucre.dao.StudentDao;
import com.oucre.pojo.Student;
import com.oucre.pojo.User;
import com.oucre.service.StudentManagerService;

@Service
public class StudentManagerServiceImpl implements StudentManagerService {

	@Autowired
	private StudentDao studentDao;

	@Override
	public Map<String, Object> findStudentSearch(Map<String, Object> map, EasyUiPager easyUiPager, User user) {
		if (user != null) {
			// map.put("uid", user.getId());
			return studentDao.findStudentSearch(map, easyUiPager);
		}
		return null;
	}

	@Override
	public AjaxJson updStudentStatus(Student student, User user) {
		AjaxJson a = new AjaxJson();
		if (studentDao.upd(student)) {
			a.setSuccess(true);
			a.setMsg("操作成功");
		}
		return a;
	}

	@Override
	public AjaxJson updStudentConver(Student student, User user) {
		AjaxJson a = new AjaxJson();
		if((Pub.STATUS_VARIFY_INFO_SUCCESS.equals(student.getStatus()) && user.getId().equals(student.getUid())
				&& user.getRoleid() == Pub.ROLE_ID_USER_INTRODUCER)){
			student.setCuid(user.getId());
			if (studentDao.upd(student)) {
				a.setSuccess(true);
				a.setMsg("操作成功");
			}
		}else{
			a.setSuccess(false);
			a.setMsg("数据在录入审核成功状态并且是本人录入的数据，线下转化成功后才可以提交转化成功申请。");
		}
		return a;
	}

	@Override
	public AjaxJson addStudent(Student student, User user) {
		AjaxJson a = new AjaxJson();
		if (user != null) {
			if (studentDao.findStudentByQQ(student.getQq()) == null) {
				student.setUid(user.getId());
				if (Pub.CONVER_BY_MYSELF.equals(student.getConver())) {
					student.setDays(Pub.CONVER_DAYS);
				}
				student.setStatus(Pub.STATUS_APPLY_ENTRING);
				if (studentDao.add(student) > 0) {
					a.setSuccess(true);
					a.setMsg("操作成功");
				} else {
					a.setSuccess(false);
				}
			} else {
				a.setSuccess(false);
				a.setMsg("QQ号码已存在");
			}
		}
		return a;
	}

	@Override
	public AjaxJson delStudent(Integer id, User user) {
		AjaxJson a = new AjaxJson();
		Student stu = studentDao.findById(id);
		if (ValidateUtil.isInt(id + "")) {
			// 如果是录入状态，并且是本人数据，本人是介绍人，则可以删除。
			// 或者是审批人，也可以删除
			if ((Pub.STATUS_APPLY_ENTRING.equals(stu.getStatus()) && user.getId().equals(stu.getUid())
					&& user.getRoleid() == Pub.ROLE_ID_USER_INTRODUCER) || user.getRoleid()==Pub.ROLE_ID_USER_VERIFYER) {
				if (studentDao.del(id)) {
					a.setSuccess(true);
					a.setMsg("操作成功");
				} else {
					a.setSuccess(false);
					a.setMsg("操作失败");
				}
			} else {
				a.setSuccess(false);
				a.setMsg("数据在录入状态并且是本人录入的数据才可以删除。");
			}
		} else {
			a.setSuccess(false);
			a.setMsg("操作失败");
		}
		return a;
	}

	@Override
	public List<Student> findAllStudents() {
		return studentDao.findAllStudents();
	}

	@Override
	public AjaxJson updStudent(Student stu, User user) {
		AjaxJson a = new AjaxJson();
		if ((Pub.STATUS_APPLY_ENTRING.equals(stu.getStatus()) && user.getId().equals(stu.getUid())
				&& user.getRoleid() == Pub.ROLE_ID_USER_INTRODUCER) || user.getRoleid()==Pub.ROLE_ID_USER_VERIFYER) {
			if("0".equals(stu.getConver())){// 任意人可以转化，则把转化日期置为0，反之则置为30天
				stu.setDays(null);
			}else{
				stu.setDays(Pub.CONVER_DAYS);
			}
			if (studentDao.upd(stu)) {
				a.setSuccess(true);
				a.setMsg("操作成功");
			}
		}else{
			a.setSuccess(false);
			a.setMsg("数据在录入状态并且是本人录入的数据才可以修改。");
		}
		return a;
	}

}
