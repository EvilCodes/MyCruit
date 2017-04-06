package com.oucre.dao;

import java.util.Map;

import com.oucre.core.dao.BaseDao;
import com.oucre.core.mode.search.EasyUiPager;
import com.oucre.pojo.SendMessage;

public interface SendMessageDao extends BaseDao<SendMessage>{
	public Map<String, Object> findSendMessageSearch(Map<String, Object> map, EasyUiPager easyUiPager);

	public SendMessage findSendMessageByGnum(String gnum);

}
