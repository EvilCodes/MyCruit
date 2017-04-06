package com.oucre.service;

import java.util.Map;

import com.oucre.core.mode.json.AjaxJson;
import com.oucre.core.mode.search.EasyUiPager;
import com.oucre.pojo.Resource;

public interface ResourceManagerService {
	public Map<String, Object> findResourceSearch(Map<String, Object> map,
			EasyUiPager easyUiPager);

	public AjaxJson updResource(Resource resource);

	public AjaxJson addResource(Resource resource);

	public AjaxJson delResource(String id);
}
