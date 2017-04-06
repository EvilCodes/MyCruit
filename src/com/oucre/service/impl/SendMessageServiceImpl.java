package com.oucre.service.impl;

import java.sql.Timestamp;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oucre.core.mode.json.AjaxJson;
import com.oucre.core.mode.search.EasyUiPager;
import com.oucre.core.util.ValidateUtil;
import com.oucre.dao.SendMessageDao;
import com.oucre.pojo.SendMessage;
import com.oucre.pojo.Student;
import com.oucre.pojo.User;
import com.oucre.service.SendMessageService;

@Service
public class SendMessageServiceImpl implements SendMessageService {
	@Autowired
	private SendMessageDao sendMessageDao;

	@Override
	public Map<String, Object> findSendMessageSearch(Map<String, Object> map, EasyUiPager easyUiPager) {
		return sendMessageDao.findSendMessageSearch(map, easyUiPager);
	}

	@Override
	public AjaxJson addSendMessage(SendMessage entity,User user) {
		AjaxJson a = new AjaxJson();
		entity.setMtime(new Timestamp(System.currentTimeMillis()));
		entity.setUid(user.getId());
		if (sendMessageDao.add(entity) > 0) {
			a.setSuccess(true);
			a.setMsg("添加成功");
			return a;
		}
		return a;
	}

	@Override
	public AjaxJson delSendMessage(Integer id, User user) {
		AjaxJson a = new AjaxJson();
		if (ValidateUtil.isInt(id+"")) {
			if(sendMessageDao.findById(id).getUid()==user.getId()){
				if (sendMessageDao.del(id)) {
					a.setSuccess(true);
					a.setMsg("操作成功");
				} else {
					a.setSuccess(false);
					a.setMsg("操作失败");
				}
			}else{
				a.setSuccess(false);
				a.setMsg("只能删除自己录入的条目");
			}
		} else {
			a.setSuccess(false);
			a.setMsg("操作失败");
		}
		return a;
	}

	@Override
	public AjaxJson updSendMessage(SendMessage entity, User attribute) {
		AjaxJson a = new AjaxJson();
		entity.setMtime(new Timestamp(System.currentTimeMillis()));
		if (sendMessageDao.upd(entity)) {
			a.setSuccess(true);
			a.setMsg("操作成功");
		}else{
			a.setSuccess(false);
			a.setMsg("操作失败");
		}
		return a;
	}

}
