package com.oucre.service;

import java.util.Map;

import com.oucre.core.mode.json.AjaxJson;
import com.oucre.core.mode.search.EasyUiPager;
import com.oucre.pojo.SendMessage;
import com.oucre.pojo.User;

public interface SendMessageService {
	public Map<String, Object> findSendMessageSearch(Map<String, Object> map, EasyUiPager easyUiPager);

	public AjaxJson addSendMessage(SendMessage entity,User user);

	public AjaxJson delSendMessage(Integer id, User attribute);

	public AjaxJson updSendMessage(SendMessage entity, User attribute);
}
