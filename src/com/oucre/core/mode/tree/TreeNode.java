package com.oucre.core.mode.tree;

import java.util.List;

public class TreeNode {
	public String id;
	public String text;
	public String state;
	public boolean checked;
	public List<TreeNode> children;

	public String getId() {
		return id;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<TreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}

	public TreeNode(String id, String text, String state, boolean checked,
			List<TreeNode> children) {
		super();
		this.id = id;
		this.text = text;
		this.state = state;
		this.checked = checked;
		this.children = children;
	}

	public TreeNode() {
		super();
	}

}
