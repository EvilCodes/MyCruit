package com.oucre.dao.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.oucre.core.dao.impl.BaseDao;
import com.oucre.core.mode.search.EasyUiPager;
import com.oucre.dao.ResourceDao;
import com.oucre.pojo.Resource;

@Repository
public class ResourceDaoImpl extends BaseDao<Resource> implements ResourceDao {
	private Logger logger = Logger.getLogger(this.getClass());

	@Override
	public Integer add(Resource entity) {
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
	public boolean upd(Resource entity) {
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
			super.deleteEntity(new Resource(id));
			return true;
		} catch (Exception e) {
			logger.error("dao´íÎó", e);
			return false;
		}
	}

	@Override
	public Resource findById(Integer id) {
		try {
			return super.get(Resource.class, id);
		} catch (Exception e) {
			logger.error("dao´íÎó", e);
			return null;
		}
	}

	@Override
	public List<Resource> getModel(Integer roleid) {
		try {
			super.hql = "from Resource r where r.id in (select rr.resourceid from RoleResource rr where  rr.roleid = " + roleid + ") and r.level = 1 order by r.orderby";
			return super.findHql(hql);
		} catch (Exception e) {
			logger.error("dao²éÑ¯Ä£¿é´íÎó", e);
			return null;
		}
	}

	@Override
	public List<Resource> getMenu(Integer roleid, Integer superid) {
		try {
			super.hql = "from Resource where id in (select resourceid from RoleResource where roleid = " + roleid + ") and level = 2 and superid = " + superid + " order by orderby ";
			return super.findHql(hql);
		} catch (Exception e) {
			logger.error("dao²éÑ¯²Ëµ¥´íÎó", e);
			return null;
		}
	}

	@Override
	public List<Resource> getButton(Integer roleid, Integer superid) {
		try {
			super.hql = "from Resource where id in (select resourceid from RoleResource where roleid = " + roleid + ") and level = 3 and superid = " + superid + "  order by orderby ";
			return super.findHql(hql);
		} catch (Exception e) {
			logger.error("dao²éÑ¯°´Å¥´íÎó", e);
			return null;
		}
	}

	@Override
	public List<Resource> getAllModel() {
		try {
			super.hql = "from Resource r where  r.level = '1' order by orderby ";
			return super.findHql(hql);
		} catch (Exception e) {
			logger.error("dao²éÑ¯Ä£¿é´íÎó", e);
			return null;
		}
	}

	@Override
	public List<Resource> getAllMenu() {
		try {
			super.hql = "from Resource r where  r.level = '2' order by orderby ";
			return super.findHql(hql);
		} catch (Exception e) {
			logger.error("dao²éÑ¯Ä£¿é´íÎó", e);
			return null;
		}
	}

	@Override
	public List<Resource> getAllButton() {
		try {
			super.hql = "from Resource r  where  r.level = '3' order by orderby ";
			return super.findHql(hql);
		} catch (Exception e) {
			logger.error("dao²éÑ¯Ä£¿é´íÎó", e);
			return null;
		}
	}

	@Override
	public Map<String, Object> findResourceSearch(Map<String, Object> map, EasyUiPager easyUiPager) {
		try {
			String Hql = "from Resource where 1=1 ";
			if (map.get("name") != null) {
				Hql += "and name like '" + map.get("name") + "%' ";
			}
			String orderby = "";
			if (easyUiPager.getOrderby() != null) {
				orderby = " " + easyUiPager.getOrderby() + " ";
			}
			List<Resource> list = super.findHqlByPager(Hql + orderby, (easyUiPager.getPage() - 1) * easyUiPager.getRows(), easyUiPager.getRows());
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
	public List<Resource> isLink(Integer roleid, String link) {
		try {
			super.hql = "from RoleResource rr,Resource r where rr.resourceid = r.id and rr.roleid =" + roleid + " and r.link = '" + link + "' ";
			return super.findHql(super.hql);
		} catch (Exception e) {
			logger.error("dao´íÎó", e);
			return null;
		}
	}
}
