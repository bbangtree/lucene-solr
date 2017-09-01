package com.grove.tree.config.vo;

public class Page {
	
	private int totalPage;
	private int totalCount;
	private int rows;
	private int page;
	private int skipCnt;
	private int startRowNum;
	private int endRowNum;
	private int pagingSize;
	private int pagingStartNum;
	private int pagingEndNum;
	private boolean hasPrevPage;
	private boolean hasNextPage;
	private int prevPage;
	private int nextPage;
	
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
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getSkipCnt() {
		return skipCnt;
	}
	public void setSkipCnt(int skipCnt) {
		this.skipCnt = skipCnt;
	}
	public int getStartRowNum() {
		return startRowNum;
	}
	public void setStartRowNum(int startRowNum) {
		this.startRowNum = startRowNum;
	}
	public int getEndRowNum() {
		return endRowNum;
	}
	public void setEndRowNum(int endRowNum) {
		this.endRowNum = endRowNum;
	}
	public int getPagingSize() {
		return pagingSize;
	}
	public void setPagingSize(int pagingSize) {
		this.pagingSize = pagingSize;
	}
	public int getPagingStartNum() {
		return pagingStartNum;
	}
	public void setPagingStartNum(int pagingStartNum) {
		this.pagingStartNum = pagingStartNum;
	}
	public int getPagingEndNum() {
		return pagingEndNum;
	}
	public void setPagingEndNum(int pagingEndNum) {
		this.pagingEndNum = pagingEndNum;
	}
	public boolean isHasPrevPage() {
		return hasPrevPage;
	}
	public void setHasPrevPage(boolean hasPrevPage) {
		this.hasPrevPage = hasPrevPage;
	}
	public boolean isHasNextPage() {
		return hasNextPage;
	}
	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}
	public int getPrevPage() {
		return prevPage;
	}
	public void setPrevPage(int prevPage) {
		this.prevPage = prevPage;
	}
	public int getNextPage() {
		return nextPage;
	}
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	

}
