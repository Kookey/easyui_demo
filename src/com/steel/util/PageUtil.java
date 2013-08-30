package com.steel.util;

public class PageUtil {

	public static  <T> Page<T> instancePage(int page,int rows){
		page = getPage(page);
		rows = getRows(rows);
		int beginPage = getBeginPage(rows, page);
		return new Page<T>(page,rows,beginPage);
	}
	
	public static int getPage(int page){
		return page==0?1:page;
	}
	public static int getRows(int rows){
		return rows==0?10:rows;
	}
	public static int getBeginPage(int rows,int page){
		return (page-1)*rows;
	}
}
