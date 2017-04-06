package com.oucre.service;

import java.util.Map;

import com.oucre.core.mode.json.AjaxJson;
import com.oucre.core.mode.search.EasyUiPager;
import com.oucre.pojo.User;

public interface PartTimersManagerService {

	public Map<String, Object> findPartTimersSearch(Map<String, Object> map, EasyUiPager easyUiPager, Object o);

	public AjaxJson addPartTimers(User user, Object o);

	public AjaxJson updPartTimers(User user, Object o);

	public AjaxJson delPartTimers(Integer id, Object o);

	public AjaxJson updPartTimersPassword(User user, Object o);

	public AjaxJson updPartTimersStatus(User user, Object o);
}
