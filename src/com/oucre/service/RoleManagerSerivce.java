package com.oucre.service;

import java.util.List;
import java.util.Map;

import com.oucre.core.mode.json.AjaxJson;
import com.oucre.core.mode.search.EasyUiPager;
import com.oucre.core.mode.tree.TreeNode;
import com.oucre.pojo.Role;

public interface RoleManagerSerivce {

	public Map<String, Object> findRoleSearch(Map<String, Object> map,
			EasyUiPager easyUiPager);

	public AjaxJson updRole(Role role);

	public AjaxJson addRole(Role role);

	public AjaxJson delRole(String id);

	public List<TreeNode> getRoleResource(Integer roleid);

	public AjaxJson updRoleResource(List<TreeNode> checked,
			List<TreeNode> unchecked, List<TreeNode> indeterminate,
			Integer roleid);

}
