package com.oucre.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.oucre.core.dao.impl.BaseDao;
import com.oucre.dao.RoleResourceDao;
import com.oucre.pojo.RoleResource;

@Repository
public class RoleResoucreDaoImpl extends BaseDao<RoleResource> implements
		RoleResourceDao {
	private Logger logger = Logger.getLogger(this.getClass());

	@Override
	public Integer add(RoleResource entity) {
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
	public boolean upd(RoleResource entity) {
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
			super.deleteEntity(new RoleResource(id));
			return true;
		} catch (Exception e) {
			logger.error("dao´íÎó", e);
			return false;
		}
	}

	@Override
	public RoleResource findById(Integer id) {
		try {
			return super.get(RoleResource.class, id);
		} catch (Exception e) {
			logger.error("dao´íÎó", e);
			return null;
		}
	}

	@Override
	public boolean findRoleResource(Integer roleid, Integer resourceid) {
		try {
			super.hql = "from RoleResource where roleid = " + roleid
					+ " resourceid = " + resourceid;
			List<RoleResource> list = super.findHql(hql);
			if (list != null && list.size() > 0) {
				return true;
			}
			return false;
		} catch (Exception e) {
			logger.error("dao´íÎó", e);
			return false;
		}

	}

	@Override
	public List<RoleResource> findRoleResourceList(Integer roleid) {
		try {
			super.hql = "from RoleResource where roleid =" + roleid;
			return super.findHql(hql);
		} catch (Exception e) {
			logger.error("dao´íÎó", e);
			return null;
		}
	}

	@Override
	public boolean dels(List<RoleResource> entitys) {
		try {
			super.deleteAll(entitys);
			return true;
		} catch (Exception e) {
			logger.error("dao´íÎó", e);
			return false;
		}
	}

	@Override
	public boolean adds(List<RoleResource> entitys) {
		try {
			super.saveAll(entitys);
			return true;
		} catch (Exception e) {
			logger.error("dao´íÎó", e);
			return false;
		}
	}

}
