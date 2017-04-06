package com.oucre.dao;

import java.util.List;
import java.util.Map;

import com.oucre.core.dao.BaseDao;
import com.oucre.core.mode.search.EasyUiPager;
import com.oucre.pojo.Role;

public interface RoleDao extends BaseDao<Role> {

	public Map<String, Object> findRoleSearch(Map<String, Object> map,
			EasyUiPager easyUiPager);

	public List<Role> findAllRole();

	public List<Role> findAllRoleGroups(String groups);
}
