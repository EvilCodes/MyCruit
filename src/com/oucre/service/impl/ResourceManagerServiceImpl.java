package com.oucre.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oucre.core.mode.json.AjaxJson;
import com.oucre.core.mode.search.EasyUiPager;
import com.oucre.core.util.ValidateUtil;
import com.oucre.dao.ResourceDao;
import com.oucre.pojo.Resource;
import com.oucre.service.ResourceManagerService;

@Service
public class ResourceManagerServiceImpl implements ResourceManagerService {

	@Autowired
	private ResourceDao resourceDao;

	@Override
	public Map<String, Object> findResourceSearch(Map<String, Object> map,
			EasyUiPager easyUiPager) {
		return resourceDao.findResourceSearch(map, easyUiPager);
	}

	@Override
	public AjaxJson updResource(Resource resource) {
		AjaxJson a = new AjaxJson();
		if (resourceDao.upd(resource)) {
			a.setSuccess(true);
			a.setMsg("操作成功");
		}
		return a;
	}

	@Override
	public AjaxJson addResource(Resource Resource) {
		AjaxJson a = new AjaxJson();
		if (resourceDao.add(Resource) > 0) {
			a.setSuccess(true);
			a.setMsg("操作成功");
		} else {
			a.setSuccess(false);
		}
		return a;
	}

	@Override
	public AjaxJson delResource(String id) {
		AjaxJson a = new AjaxJson();
		if (ValidateUtil.isInt(id)) {
			if (resourceDao.del(Integer.parseInt(id))) {
				a.setSuccess(true);
				a.setMsg("操作成功");
			} else {
				a.setSuccess(true);
				a.setMsg("操作成功");
			}
		} else {
			a.setSuccess(true);
			a.setMsg("操作成功");
		}
		return a;
	}
}
