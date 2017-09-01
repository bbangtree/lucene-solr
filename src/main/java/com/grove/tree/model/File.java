package com.grove.tree.model;

import java.util.Date;

public class File {

	private int seq;
	private int board_seq;
	private String original_file_nm;
	private String stored_file_nm;
	private String file_path;
	private int file_size;
	private String reg_seq;
	private Date reg_dt;
	private String del_fl;
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
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
	public String getDel_fl() {
		return del_fl;
	}
	public void setDel_fl(String del_fl) {
		this.del_fl = del_fl;
	}

}
