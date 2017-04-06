package com.oucre.service;

import java.util.List;

import com.oucre.core.mode.json.AjaxJson;
import com.oucre.core.mode.module.ModelInfo;
import com.oucre.pojo.Resource;
import com.oucre.pojo.Role;
import com.oucre.pojo.SendMessage;
import com.oucre.pojo.Student;
import com.oucre.pojo.User;

public interface BusiCommonService {

	public List<Role> findAllRole();

	public List<Role> findAllRoleGroups(String isAdmin);

	/**
	 * 角色对应资源判断
	 * 
	 * @date 2015年4月16日 上午9:55:45
	 * @author haoli_jun
	 * @param roleid
	 * @param link
	 * @return
	 */
	public boolean isResources(Integer roleid, String link);

	public List<Resource> getButton(Integer roleid, Integer superid);

	public List<ModelInfo> getMenu(Integer role);

	public AjaxJson updUserPassword(String passworda, String paswrod, Object obj);

	public AjaxJson updUser(String username, Object obj);

	public List<User> findAllUsers();

	Student findStudentByQQ(String qq);
	
	SendMessage findSendMessageByGnum(String gnum);
}
