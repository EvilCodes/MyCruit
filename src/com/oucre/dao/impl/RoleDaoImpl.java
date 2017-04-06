package com.oucre.dao.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.oucre.core.dao.impl.BaseDao;
import com.oucre.core.mode.search.EasyUiPager;
import com.oucre.dao.RoleDao;
import com.oucre.pojo.Role;

@Repository
public class RoleDaoImpl extends BaseDao<Role> implements RoleDao {
	private Logger logger = Logger.getLogger(this.getClass());

	@Override
	public Integer add(Role entity) {
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
	public boolean upd(Role entity) {
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
			super.deleteEntity(new Role(id));
			return true;
		} catch (Exception e) {
			logger.error("dao´íÎó", e);
			return false;
		}
	}

	@Override
	public Role findById(Integer id) {
		try {
			return super.get(Role.class, id);
		} catch (Exception e) {
			logger.error("dao´íÎó", e);
			return null;
		}

	}

	@Override
	public Map<String, Object> findRoleSearch(Map<String, Object> map,
			EasyUiPager easyUiPager) {
		try {
			String Hql = "from Role where 1=1 ";
			if (map.get("name") != null) {
				Hql += "and name like '" + map.get("name") + "%' ";
			}
			String orderby = "";
			if (easyUiPager.getOrderby() != null) {
				orderby = " " + easyUiPager.getOrderby() + " ";
			}
			List<Role> list = super.findHqlByPager(Hql + orderby,
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
	public List<Role> findAllRole() {
		try {
			return super.findHql("from Role ");
		} catch (Exception e) {
			logger.error("dao´íÎó", e);
			return null;
		}
	}

	@Override
	public List<Role> findAllRoleGroups(String groups) {
		try {
			return super.findHql("from Role where groups = '" + groups + "' ");
		} catch (Exception e) {
			logger.error("dao´íÎó", e);
			return null;
		}
	}

}
