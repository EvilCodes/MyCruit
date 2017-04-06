package com.oucre.service;

import java.util.Map;

import com.oucre.core.mode.json.AjaxJson;
import com.oucre.core.mode.search.EasyUiPager;
import com.oucre.pojo.Ticket;
import com.oucre.pojo.User;

public interface TrainTicketsManagerService {
	Map<String, Object> findTrainTicketsSearch(Map<String, Object> map,
			EasyUiPager easyUiPager, User user);

	AjaxJson addTrainTickets(Ticket ticket, User attribute);

	AjaxJson delTrainTickets(Integer id, User attribute);
}
