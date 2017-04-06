package com.oucre.dao;

import java.util.Map;

import com.oucre.core.dao.BaseDao;
import com.oucre.core.mode.search.EasyUiPager;
import com.oucre.pojo.Ticket;

public interface TrainTicketsDao extends BaseDao<Ticket>{
	/**
	 * ��������ҳ��ѯѧ������
	 * @param map
	 * @param easyUiPager
	 * @return
	 */
	Map<String,Object> findTrainTicketsSearch(Map<String,Object> map,EasyUiPager easyUiPager);
	
	/**
	 * ��ѯȫ��
	 * @return
	 *//*
	List<Student> findAllStudents();

	*//**
	 * ����qq�������ѧ����Ϣ
	 * @param qq
	 * @return
	 *//*
	Student findStudentByQQ(String qq);*/
}
