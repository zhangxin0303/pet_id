package com.cqcej.web.modules.app.utils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * APP分页数据
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-04-18 16:32
 */
@SuppressWarnings("unused")
@ApiModel("分页信息")
public class AppPage<T> {
	/**
	 * 当前页
	 */
	@ApiModelProperty("当前页")
	private int currPage = 1;
	
	/**
	 * 每页多少条数据
	 */
	@ApiModelProperty("每页多少条数据")
	private int pageSize;
	
	/**
	 * 总共多少页
	 */
	@ApiModelProperty("总共多少页")
	private int totalPage;
	
	/**
	 * 总共多少条数据
	 */
	@ApiModelProperty("总共多少条数据")
	private int totalCount;
	
	public AppPage(int totalCount, int pageSize) {
		this.totalCount = totalCount;
		this.pageSize = pageSize;
		this.totalPage = (int) Math.ceil(1.0f * totalCount / pageSize);
	}
	
	public AppPage(int totalCount, int pageSize, List<T> pages) {
		this(totalCount, pageSize);
		this.pages = pages;
	}
	
	/**
	 * 分页数据
	 */
	@ApiModelProperty("分页数据")
	private List<T> pages;
	
	public int getCurrPage() {
		return currPage;
	}
	
	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public int getTotalPage() {
		return totalPage;
	}
	
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
	public int getTotalCount() {
		return totalCount;
	}
	
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
	public List<T> getPages() {
		return pages;
	}
	
	public void setPages(List<T> pages) {
		this.pages = pages;
	}
}
