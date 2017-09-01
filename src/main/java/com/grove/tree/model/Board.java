package com.grove.tree.model;

import java.util.Date;
import java.util.List;

public class Board {
	
	private int seq;
	private String title;
	private String contents;
	private String url;
	private String reg_seq;
	private Date reg_dt;
	private String mod_seq;
	private Date mod_dt;
	private String del_fl;
	private int read_cnt;
	
	private int rows;
	private int page;
	private int edit;
	private String search_type;
	private String search;
	private String chosung;
	private String index;
	
	private int board_seq;
	private String original_file_nm;
	private String stored_file_nm;
	private String file_path;
	private int file_size;
	
	private List<String> snippets;
	private String highlight;
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getReg_seq() {
		return reg_seq;
	}
	public void setReg_seq(String reg_seq) {
		this.reg_seq = reg_seq;
	}
	public Date getReg_dt() {
		return reg_dt;
	}
	public void setReg_dt(Date reg_dt) {
		this.reg_dt = reg_dt;
	}
	public String getMod_seq() {
		return mod_seq;
	}
	public void setMod_seq(String mod_seq) {
		this.mod_seq = mod_seq;
	}
	public Date getMod_dt() {
		return mod_dt;
	}
	public void setMod_dt(Date mod_dt) {
		this.mod_dt = mod_dt;
	}
	public String getDel_fl() {
		return del_fl;
	}
	public void setDel_fl(String del_fl) {
		this.del_fl = del_fl;
	}
	public int getRead_cnt() {
		return read_cnt;
	}
	public void setRead_cnt(int read_cnt) {
		this.read_cnt = read_cnt;
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
	public int getEdit() {
		return edit;
	}
	public void setEdit(int edit) {
		this.edit = edit;
	}
	public String getSearch_type() {
		return search_type;
	}
	public void setSearch_type(String search_type) {
		this.search_type = search_type;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public String getChosung() {
		return chosung;
	}
	public void setChosung(String chosung) {
		this.chosung = chosung;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public int getBoard_seq() {
		return board_seq;
	}
	public void setBoard_seq(int board_seq) {
		this.board_seq = board_seq;
	}
	public String getOriginal_file_nm() {
		return original_file_nm;
	}
	public void setOriginal_file_nm(String original_file_nm) {
		this.original_file_nm = original_file_nm;
	}
	public String getStored_file_nm() {
		return stored_file_nm;
	}
	public void setStored_file_nm(String stored_file_nm) {
		this.stored_file_nm = stored_file_nm;
	}
	public String getFile_path() {
		return file_path;
	}
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
	public int getFile_size() {
		return file_size;
	}
	public void setFile_size(int file_size) {
		this.file_size = file_size;
	}
	public List<String> getSnippets() {
		return snippets;
	}
	public void setSnippets(List<String> snippets) {
		this.snippets = snippets;
	}
	public String getHighlight() {
		return highlight;
	}
	public void setHighlight(String highlight) {
		this.highlight = highlight;
	}
}
