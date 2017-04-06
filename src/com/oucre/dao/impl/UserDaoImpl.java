package com.oucre.dao.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.oucre.core.dao.impl.BaseDao;
import com.oucre.core.mode.search.EasyUiPager;
import com.oucre.dao.UserDao;
import com.oucre.pojo.User;

@Repository
public class UserDaoImpl extends BaseDao<User> implements UserDao {

	private Logger logger = Logger.getLogger(this.getClass());

	@Override
	public Integer add(User entity) {
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
	public boolean upd(User entity) {
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
			super.deleteEntity(new User(id));
			return true;
		} catch (Exception e) {
			logger.error("dao´íÎó", e);
			return false;
		}
	}

	@Override
	public User findById(Integer id) {
		try {
			return super.get(User.class, id);
		} catch (Exception e) {
			logger.error("dao´íÎó", e);
			return null;
		}
	}

	@Override
	public User loginUser(String name, String password) {
		try {
			String hql = "from User where tel ='" + name + "' and password = '"
					+ password + "' ";
			List<User> list = super.findHql(hql);
			if (list != null && list.size() > 0) {
				return list.get(0);
			}
			return null;
		} catch (Exception e) {
			logger.error("dao´íÎó", e);
			return null;
		}
	}

	@Override
	public List<User> findUserRoleid(Integer roleid) {
		try {
			String hql = "from User where roleid =" + roleid;
			List<User> list = super.findHql(hql);
			if (list != null && list.size() > 0) {
				return list;
			}
			return null;
		} catch (Exception e) {
			logger.error("dao´íÎó", e);
			return null;
		}
	}

	@Override
	public Map<String, Object> findUserSearch(Map<String, Object> map,
			EasyUiPager easyUiPager) {
		try {
			String Hql = "from User where 1=1 ";
			if (map.get("uid") != null) {
				Hql += "and uid = " + map.get("uid") + " ";
			}
			if (map.get("keyword") != null) {
				Hql += "and (username like '" + map.get("keyword")
						+ "%' or tel like  '" + map.get("keyword") + "%') ";
			}
			String orderby = "";
			if (easyUiPager.getOrderby() != null) {
				orderby = " " + easyUiPager.getOrderby() + " ";
			}
			List<User> list = super.findHqlByPager(Hql + orderby,
					(easyUiPager.getPage() - 1) * easyUiPager.getRows(),
					easyUiPager.getRows());
			Integer total = super
					.findByHqlForPageCountEasyUi("select COUNT(*) " + Hql);
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
	public boolean findExistTel(String tel) {
		try {
			List<User> u = super.findHql("from User where tel ='" + tel + "' ");
			if (u != null && u.size() > 0) {
				return false;
			}
			return true;
		} catch (Exception e) {
			logger.error("dao´íÎó", e);
			return false;
		}
	}

	@Override
	public List<User> findEnterpriseUser(Integer eid) {
		try {
			return super.findHql("from User where eid = " + eid);
		} catch (Exception e) {
			logger.error("dao´íÎó", e);
			return null;
		}
	}
	/*
	 * @Override public User findUserTel(String tel) { try { List<User> u =
	 * super.find("from User where tel ='" + tel + "' "); if (u != null &&
	 * u.size() > 0) { return u.get(0); } return null; } catch (Exception e) {
	 * logger.error("dao³ö´í", e); return null; } }
	 */

	@Override
	public List<User> findAllUsers() {
		try {
			return super.findHql("from User");
		} catch (Exception e) {
			logger.error("dao´íÎó", e);
			return null;
		}
	}

}
