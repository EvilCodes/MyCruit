package com.oucre.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oucre.core.mode.json.AjaxJson;
import com.oucre.core.mode.search.EasyUiPager;
import com.oucre.core.mode.tree.TreeNode;
import com.oucre.core.util.ValidateUtil;
import com.oucre.dao.ResourceDao;
import com.oucre.dao.RoleDao;
import com.oucre.dao.RoleResourceDao;
import com.oucre.dao.UserDao;
import com.oucre.pojo.Resource;
import com.oucre.pojo.Role;
import com.oucre.pojo.RoleResource;
import com.oucre.service.RoleManagerSerivce;

@Service
public class RoleManagerServiceImpl implements RoleManagerSerivce {

	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private RoleResourceDao roleResourceDao;

	@Autowired
	private ResourceDao resourceDao;

	@Override
	public Map<String, Object> findRoleSearch(Map<String, Object> map,
			EasyUiPager easyUiPager) {
		return roleDao.findRoleSearch(map, easyUiPager);
	}

	@Override
	public AjaxJson updRole(Role role) {
		AjaxJson a = new AjaxJson();
		if (roleDao.upd(role)) {
			a.setSuccess(true);
			a.setMsg("操作成功");
		}
		return a;
	}

	@Override
	public AjaxJson addRole(Role role) {
		AjaxJson a = new AjaxJson();
		if (roleDao.add(role) > 0) {
			a.setSuccess(true);
			a.setMsg("操作成功");
		} else {
			a.setSuccess(false);
		}
		return a;
	}

	@Override
	public AjaxJson delRole(String id) {
		AjaxJson a = new AjaxJson();
		if (ValidateUtil.isInt(id)) {
			Integer iid = Integer.parseInt(id);
			if (userDao.findUserRoleid(iid) == null) {
				if (roleDao.del(iid)) {
					a.setSuccess(true);
					a.setMsg("操作成功");
				}
			} else {
				a.setSuccess(false);
				a.setMsg("角色已关联用户删除失败，如想删除请删除角色写所有用户!");
			}
		} else {
			a.setSuccess(false);
			a.setMsg("参数类型出现问题");
		}
		return a;
	}

	@Override
	public List<TreeNode> getRoleResource(Integer roleid) {
		try {
			List<Resource> Model = resourceDao.getAllModel();
			List<Resource> Menu = resourceDao.getAllMenu();
			List<Resource> Button = resourceDao.getAllButton();
			List<RoleResource> rr = roleResourceDao
					.findRoleResourceList(roleid);
			List<TreeNode> treenode1 = new ArrayList<TreeNode>();
			for (int i = 0; i < Model.size(); i++) {
				List<TreeNode> treenode2 = new ArrayList<TreeNode>();
				for (int j = 0; j < Menu.size(); j++) {
					List<TreeNode> treenode3 = new ArrayList<TreeNode>();
					for (int s = 0; s < Button.size(); s++) {
						if (Menu.get(j).getId()
								.equals(Button.get(s).getSuperid())) {
							boolean flag = false;
							for (int k = 0; k < rr.size(); k++) {
								if (Button.get(s).getId()
										.equals(rr.get(k).getResourceid())) {
									flag = true;
								}
							}
							treenode3.add(new TreeNode(Button.get(s).getId()
									+ "", Button.get(s).getName(), "open",
									flag, null));
						}
					}
					if (Model.get(i).getId().equals(Menu.get(j).getSuperid())) {
						treenode2
								.add(new TreeNode(Menu.get(j).getId() + "",
										Menu.get(j).getName(), "open", false,
										treenode3));
					}
				}
				treenode1.add(new TreeNode(Model.get(i).getId() + "", Model
						.get(i).getName(), "open", false, treenode2));
			}
			return treenode1;
		} catch (Exception e) {
			logger.error("资源异常", e);
			return null;
		}
	}

	public AjaxJson updRoleResource(List<TreeNode> checked,
			List<TreeNode> unchecked, List<TreeNode> indeterminate,
			Integer roleid) {
		AjaxJson a = new AjaxJson();
		if (roleid != null) {
			List<RoleResource> list = roleResourceDao
					.findRoleResourceList(roleid);
			roleResourceDao.dels(list);
			List<RoleResource> list2 = new ArrayList<RoleResource>();
			if (indeterminate != null) {
				for (int i = 0; i < indeterminate.size(); i++) {
					list2.add(new RoleResource(roleid, Integer
							.parseInt(indeterminate.get(i).getId())));
				}
			}
			if (checked != null) {
				for (int i = 0; i < checked.size(); i++) {
					list2.add(new RoleResource(roleid, Integer.parseInt(checked
							.get(i).getId())));
				}
			}
			roleResourceDao.adds(list2);
			a.setSuccess(true);
			a.setMsg("操作成功");
		}
		return a;
	}
}
