package com.oucre.dao.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.oucre.core.dao.impl.BaseDao;
import com.oucre.core.mode.search.EasyUiPager;
import com.oucre.dao.SendMessageDao;
import com.oucre.pojo.SendMessage;
import com.oucre.pojo.Student;

@Repository
public class SendMessageDaoImpl extends BaseDao<SendMessage> implements SendMessageDao {

	private Logger logger = Logger.getLogger(this.getClass());

	@Override
	public Integer add(SendMessage entity) {
		try {
			Serializable sz = super.saveEntity(entity);
			if (sz != null) {
				return (Integer) sz;
			}
			return 0;
		} catch (Exception e) {
			logger.error("daoÌí¼Ó´íÎó", e);
			return 0;
		}
	}

	@Override
	public boolean upd(SendMessage entity) {
		try {
			super.updateEntity(entity);
		} catch (Exception e) {
			logger.error("daoĞŞ¸Ä´íÎó", e);
			return false;
		}
		return true;
	}

	@Override
	public boolean del(Integer id) {
		try {
			super.deleteEntity(new SendMessage(id));
			return true;
		} catch (Exception e) {
			logger.error("dao²éÕÒ´íÎó", e);
			return false;
		}
	}

	@Override
	public SendMessage findById(Integer id) {
		try {
			return super.get(SendMessage.class, id);
		} catch (Exception e) {
			logger.error("dao²éÕÒ´íÎó", e);
			return null;
		}
	}

	@Override
	public Map<String, Object> findSendMessageSearch(Map<String, Object> map, EasyUiPager easyUiPager) {
		try {
			String Hql = " from SendMessage where 1=1 ";
			String orderby = "";
			if (map.get("gnum") != null) {
				Hql += " and gnum like '%" + map.get("gnum") + "%' ";
			}
			if (map.get("gname") != null) {
				Hql += " and gname like '%" + map.get("gname") + "%' ";
			}
			if (map.get("message") != null) {
				Hql += " and message like '%" + map.get("message") + "%' ";
			}
			if (easyUiPager.getOrderby() != null) {
				orderby = " " + easyUiPager.getOrderby() + " ";
			}
			List<SendMessage> list = super.findHqlByPager(/* select + */Hql + orderby,
					(easyUiPager.getPage() - 1) * easyUiPager.getRows(), easyUiPager.getRows());
			Hql = "select COUNT(*) " + Hql;
			Integer total = super.findByHqlForPageCountEasyUi(Hql);
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("rows", list);
			m.put("total", total);
			return m;
		} catch (Exception e) {
			logger.error("dao´íÎó", e);
			return null;
		}
	}

	@Override
	public SendMessage findSendMessageByGnum(String gnum) {
		List<SendMessage> sendMessageList = super.findHql("from SendMessage where gnum = "+gnum);
		if(sendMessageList.size()>=1){
			return sendMessageList.get(0);
		}else{
			return null;
		}
	}

}
