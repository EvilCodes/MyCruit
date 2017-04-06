package com.oucre.dao;

import java.util.List;

import com.oucre.core.dao.BaseDao;
import com.oucre.pojo.RoleResource;

public interface RoleResourceDao extends BaseDao<RoleResource> {
	/**
	 * 判断角色有没有该资源
	 * 
	 * @date 2015年4月8日 上午11:14:24
	 * @author haoli_jun
	 * @param roleid
	 * @param resourceid
	 * @return
	 */
	public boolean findRoleResource(Integer roleid, Integer resourceid);

	/**
	 * 根据角色查询全部资源
	 * 
	 * @date 2015年4月8日 上午11:14:14
	 * @author haoli_jun
	 * @param roleid
	 * @return
	 */
	public List<RoleResource> findRoleResourceList(Integer roleid);

	/**
	 * 多ID删除
	 * 
	 * @date 2015年4月8日 下午4:51:56
	 * @author haoli_jun
	 * @param entitys
	 * @return
	 */

	public boolean dels(List<RoleResource> entitys);

	/**
	 * 多条插入
	 * 
	 * @date 2015年4月8日 下午4:59:36
	 * @author haoli_jun
	 * @param entitys
	 * @return
	 */
	public boolean adds(List<RoleResource> entitys);

}
