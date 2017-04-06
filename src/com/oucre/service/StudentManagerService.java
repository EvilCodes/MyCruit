package com.oucre.service;

import java.util.List;
import java.util.Map;

import com.oucre.core.mode.json.AjaxJson;
import com.oucre.core.mode.search.EasyUiPager;
import com.oucre.pojo.Student;
import com.oucre.pojo.User;

public interface StudentManagerService {
	Map<String, Object> findStudentSearch(Map<String, Object> map,
			EasyUiPager easyUiPager, User user);

	AjaxJson updStudentStatus(Student student, User user);

	AjaxJson addStudent(Student student, User user);

	AjaxJson delStudent(Integer id, User user);

	AjaxJson updStudentConver(Student student, User attribute);
	
	List<Student> findAllStudents();

	/**
	 * ÐÞ¸ÄÑ§Éú
	 * @param student
	 * @param attribute
	 * @return
	 */
	AjaxJson updStudent(Student student, User user);
}
