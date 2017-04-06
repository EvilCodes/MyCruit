package com.oucre.dao;

import java.util.List;
import java.util.Map;

import com.oucre.core.dao.BaseDao;
import com.oucre.core.mode.search.EasyUiPager;
import com.oucre.pojo.Resource;

public interface ResourceDao extends BaseDao<Resource> {

	public List<Resource> getModel(Integer roleid);

	public List<Resource> getMenu(Integer roleid, Integer superid);

	public List<Resource> getButton(Integer roleid, Integer superid);

	public List<Resource> getAllModel();

	public List<Resource> getAllMenu();

	public List<Resource> getAllButton();

	public Map<String, Object> findResourceSearch(Map<String, Object> map,
			EasyUiPager easyUiPager);

	public List<Resource> isLink(Integer role, String link);
}
