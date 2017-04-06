package com.oucre.dao;

import java.util.Map;

import com.oucre.core.dao.BaseDao;
import com.oucre.core.mode.search.EasyUiPager;
import com.oucre.pojo.Ticket;

public interface TrainTicketsDao extends BaseDao<Ticket>{
	/**
	 * 按条件分页查询学生数据
	 * @param map
	 * @param easyUiPager
	 * @return
	 */
	Map<String,Object> findTrainTicketsSearch(Map<String,Object> map,EasyUiPager easyUiPager);
	
	/**
	 * 查询全部
	 * @return
	 *//*
	List<Student> findAllStudents();

	*//**
	 * 根据qq号码查找学生信息
	 * @param qq
	 * @return
	 *//*
	Student findStudentByQQ(String qq);*/
}
