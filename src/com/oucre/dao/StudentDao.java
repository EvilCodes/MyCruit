package com.oucre.dao;

import java.util.List;
import java.util.Map;

import com.oucre.core.dao.BaseDao;
import com.oucre.core.mode.search.EasyUiPager;
import com.oucre.pojo.Student;

public interface StudentDao extends BaseDao<Student>{
	/**
	 * 按条件分页查询学生数据
	 * @param map
	 * @param easyUiPager
	 * @return
	 */
	Map<String,Object> findStudentSearch(Map<String,Object> map,EasyUiPager easyUiPager);
	
	/**
	 * 查询全部
	 * @return
	 */
	List<Student> findAllStudents();

	/**
	 * 根据qq号码查找学生信息
	 * @param qq
	 * @return
	 */
	Student findStudentByQQ(String qq);
}
