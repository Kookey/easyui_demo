package com.steel.util;

import java.util.ArrayList;
import java.util.List;

public class Page<T> {

	private int page;//第几页
	private int pageSize;//每页显示多少行
	private int beginPage;//起始页
	private List<T> rows = new ArrayList<T>();
	private int total;
	
	public Page(int page,int pageSize,int beginPage){
		this.page = page;
		this.pageSize = pageSize;
		this.beginPage = beginPage;
	}
	public Page(){}

	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getBeginPage() {
		return beginPage;
	}

	public void setBeginPage(int beginPage) {
		this.beginPage = beginPage;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
}
