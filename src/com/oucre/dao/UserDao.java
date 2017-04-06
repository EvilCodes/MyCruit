package com.oucre.dao;

import java.util.List;
import java.util.Map;

import com.oucre.core.dao.BaseDao;
import com.oucre.core.mode.search.EasyUiPager;
import com.oucre.pojo.User;

public interface UserDao extends BaseDao<User> {

	public User loginUser(String name, String password);

	public List<User> findUserRoleid(Integer roleid);

	public Map<String, Object> findUserSearch(Map<String, Object> map,
			EasyUiPager easyUiPager);

	/* public User findUserTel(String tel); */

	public boolean findExistTel(String tel);

	public List<User> findEnterpriseUser(Integer eid);
	
	public List<User> findAllUsers();
}
