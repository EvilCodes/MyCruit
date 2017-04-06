package com.oucre.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oucre.core.mode.json.AjaxJson;
import com.oucre.core.mode.search.EasyUiPager;
import com.oucre.core.util.EncryptUtil;
import com.oucre.dao.UserDao;
import com.oucre.pojo.User;
import com.oucre.service.PartTimersManagerService;

@Service
public class PartTimersManagerServiceImpl implements PartTimersManagerService {

	@Autowired
	private UserDao userDao;

	@Override
	public Map<String, Object> findPartTimersSearch(Map<String, Object> map, EasyUiPager easyUiPager, Object o) {
		User u = (User) o;
		if (u != null) {
			map.put("uid", u.getId());
			return userDao.findUserSearch(map, easyUiPager);
		}
		return null;
	}

	@Override
	public AjaxJson addPartTimers(User user, Object o) {
		AjaxJson a = new AjaxJson();
		User u = (User) o;
		if (u != null) {
			if (userDao.findExistTel(user.getTel())) {
				user.setPassword(EncryptUtil.getMD5(user.getPassword()));
				user.setUid(u.getId());
				user.setStatus("Y");
				if (userDao.add(user) != null) {
					a.setSuccess(true);
					a.setMsg("添加成功");
				}
			} else {
				a.setSuccess(false);
				a.setMsg("此手机已注册过请更换手机号码");
			}
		} else {
			a.setSuccess(false);
			a.setMsg("非法操作或非企业用户");
		}
		return a;
	}

	@Override
	public AjaxJson updPartTimers(User user, Object o) {
		AjaxJson a = new AjaxJson();
		User u = (User) o;
		if (u != null) {
			if ((int) u.getId() == (int) user.getUid()) {
				User uu = userDao.findById(user.getId());
				if (uu.getTel().equals(user.getTel()) || userDao.findExistTel(user.getTel())) {
					user.setPassword(uu.getPassword());
					user.setStatus(uu.getStatus());
					if (userDao.upd(user)) {
						a.setSuccess(true);
						a.setMsg("修改成功");
					}
				} else {
					a.setSuccess(false);
					a.setMsg("此手机已注册过请更换手机号码");
				}
			} else {
				a.setSuccess(false);
				a.setMsg("您无权限操作");
			}
		} else {
			a.setSuccess(false);
			a.setMsg("非法操作或非企业用户");
		}
		return a;
	}

	@Override
	public AjaxJson delPartTimers(Integer id, Object o) {
		AjaxJson a = new AjaxJson();
		User u = (User) o;
		if (u != null) {
			User uu = userDao.findById(id);
			if ((int) uu.getUid() == (int) u.getId()) {
				if (userDao.del(id)) {
					a.setSuccess(true);
					a.setMsg("删除成功");
				}
			} else {
				a.setSuccess(false);
				a.setMsg("您无权限操作");
			}
		} else {
			a.setSuccess(false);
			a.setMsg("非法操作或非企业用户");
		}
		return a;
	}

	@Override
	public AjaxJson updPartTimersPassword(User user, Object o) {
		AjaxJson a = new AjaxJson();
		User u = (User) o;
		if (u != null) {
			User uu = userDao.findById(user.getId());
			if ((int) uu.getUid() == (int) u.getId()) {
				uu.setPassword(EncryptUtil.getMD5(user.getPassword()));
				if (userDao.upd(uu)) {
					a.setSuccess(true);
					a.setMsg("修改成功");
				}
			} else {
				a.setSuccess(false);
				a.setMsg("您无权限操作");
			}
		} else {
			a.setSuccess(false);
			a.setMsg("非法操作或非企业用户");
		}
		return a;
	}

	@Override
	public AjaxJson updPartTimersStatus(User user, Object o) {
		AjaxJson a = new AjaxJson();
		User u = (User) o;
		if (u != null) {
			User uu = userDao.findById(user.getId());
			if ((int) uu.getUid() == (int) u.getId()) {
				if (uu.getStatus().equals("Y")) {
					uu.setStatus("N");
				} else {
					uu.setStatus("Y");
				}
				if (userDao.upd(uu)) {
					a.setSuccess(true);
					a.setMsg("修改成功");
				}
			} else {
				a.setSuccess(false);
				a.setMsg("您无权限操作");
			}
		} else {
			a.setSuccess(false);
			a.setMsg("非法操作或非企业用户");
		}
		return a;
	}
}
