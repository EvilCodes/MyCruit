package com.oucre.core.mode.search;

public class EasyUiPager {
	private Integer rows; // 每页显示多少数据
	private Integer page;// 第几页
	private String sort;// 具体哪个字段要排序
	private String order;// 排序的方式默认 可以是 'asc' 或者 'desc', 默认值是 'asc'
	private String ids;// 选中的ID

	public String getOrderby() {
		if (sort != null && order != null) {
			return ("order by " + sort + " " + order);// 排序根据前端信息进行排序
		}
		return null;
	}

	public EasyUiPager(Integer rows, Integer page, String sort, String order) {
		super();
		this.rows = rows;
		this.page = page;
		this.sort = sort;
		this.order = order;
	}

	public EasyUiPager(Integer rows, Integer page, String sort, String order,
			String ids) {
		super();
		this.rows = rows;
		this.page = page;
		this.sort = sort;
		this.order = order;
		this.ids = ids;
	}

	public EasyUiPager() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

}
