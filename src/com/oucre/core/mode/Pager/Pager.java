package com.oucre.core.mode.Pager;

import java.util.List;

/**
 * 分页实体类
 */
public class Pager {
	private int currentPage;	//当前页号
	private int pageSize;		//页面大小
	private int pageTotal;		//总页数
	private int recordTotal;	//总记录数
	private int firstRecord;	//当前记录的第一条
	private int maxRecord;		//当前记录的最大记录数
	
	private List<?> pageData = null;

	public Pager(int currentPage, int pageSize) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
	}
	
	public Pager() {
		this.currentPage = 1;
		this.pageSize = 5;
		this.firstRecord = (this.currentPage-1)*this.pageSize;
		this.maxRecord = currentPage*this.pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageTotal() {
		return pageTotal;
	}
	public int getRecordTotal() {
		return recordTotal;
	}
	public void setRecordTotal(int recordTotal) {
		int pageNumber = 0;
		int firstNumber = 0;
		//总记录数
		this.recordTotal = recordTotal;
		//总记页数
		if(this.recordTotal%this.pageSize == 0){
			pageNumber = this.recordTotal/this.pageSize;
		}else{
			pageNumber = this.recordTotal/this.pageSize+1;
		}
		setPageTotal(pageNumber);
		
		//第一条记录
		firstNumber = (this.currentPage-1)<=0?1:(this.currentPage-1);
		setFirstRecord(firstNumber*this.pageSize+1);
		
		//最后记录
		setMaxRecord(this.currentPage*this.pageSize);
		
	}

	public int getFirstRecord() {
		return firstRecord;
	}

	public void setFirstRecord(int firstRecord) {
		this.firstRecord = firstRecord;
	}

	public int getMaxRecord() {
		return maxRecord;
	}

	public void setMaxRecord(int maxRecord) {
		this.maxRecord = maxRecord;
	}

	public List<?> getPageData() {
		return pageData;
	}

	public void setPageData(List<?> pageData) {
		this.pageData = pageData;
	}

	public void setPageTotal(int pageTotal) {
		this.pageTotal = pageTotal;
	}

}
