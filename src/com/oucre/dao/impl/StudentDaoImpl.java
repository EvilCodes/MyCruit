package com.oucre.dao.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.oucre.core.dao.impl.BaseDao;
import com.oucre.core.mode.search.EasyUiPager;
import com.oucre.dao.StudentDao;
import com.oucre.pojo.Student;
import com.oucre.pojo.User;

@Repository
public class StudentDaoImpl extends BaseDao<Student> implements StudentDao {
	private Logger logger = Logger.getLogger(this.getClass());

	@Override
	public Integer add(Student entity) {
		try {
			Serializable sz = super.saveEntity(entity);
			if (sz != null) {
				return (Integer) sz;
			}
			return 0;
		} catch (Exception e) {
			logger.error("dao´íÎó", e);
			return 0;
		}
	}

	@Override
	public boolean upd(Student entity) {
		try {
			super.updateEntity(entity);
		} catch (Exception e) {
			logger.error("dao´íÎó", e);
			return false;
		}
		return true;
	}

	@Override
	public boolean del(Integer id) {
		try {
			super.deleteEntity(new Student(id));
			return true;
		} catch (Exception e) {
			logger.error("dao´íÎó", e);
			return false;
		}
	}

	@Override
	public Student findById(Integer id) {
		try {
			return super.get(Student.class, id);
		} catch (Exception e) {
			logger.error("dao´íÎó", e);
			return null;
		}
	}

	@Override
	public Map<String, Object> findStudentSearch(Map<String, Object> map, EasyUiPager easyUiPager) {
		try {
			String Hql = "from Student where 1=1 ";
			/*if (map.get("uid") != null) {
				Hql += "and uid = " + map.get("uid") + " ";
			}*/
			if (map.get("name") != null) {
				Hql += "and name like '" + map.get("name") + "%' ";
			}
			if (map.get("mobile") != null) {
				Hql += "and mobile like '" + map.get("mobile") + "%' ";
			}
			if (map.get("qq") != null) {
				Hql += "and qq like '" + map.get("qq") + "%' ";
			}
			String orderby = "";
			if (easyUiPager.getOrderby() != null) {
				orderby = " " + easyUiPager.getOrderby() + " ";
			}
			List<Student> list = super.findHqlByPager(Hql + orderby,
					(easyUiPager.getPage() - 1) * easyUiPager.getRows(),
					easyUiPager.getRows());
			Hql = "select COUNT(*) " + Hql;
			Integer total = super.findByHqlForPageCountEasyUi(Hql);
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("rows", list);
			m.put("total", total);
			return m;
		} catch (Exception e) {
			logger.error("dao´íÎó", e);
			return null;
		}
	}

	@Override
	public List<Student> findAllStudents() {
		try {
			return super.findHql("from Student ");
		} catch (Exception e) {
			logger.error("dao´íÎó", e);
			return null;
		}
	}

	@Override
	public Student findStudentByQQ(String qq) {
		List<Student> stuList = super.findHql("from Student where qq = "+qq);
		if(stuList.size()==1){
			return stuList.get(0);
		}else{
			return null;
		}
	}

}
