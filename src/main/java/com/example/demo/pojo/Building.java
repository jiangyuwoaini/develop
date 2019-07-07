package com.example.demo.pojo;

public class Building {
    private int id;
    private String bid;
    private String bwidth;//项目介绍
    private String bsid;
    private String btime;//项目周期
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	public String getBwidth() {
		return bwidth;
	}
	public void setBwidth(String bwidth) {
		this.bwidth = bwidth;
	}
	public String getBsid() {
		return bsid;
	}
	public void setBsid(String bsid) {
		this.bsid = bsid;
	}
	public String getBtime() {
		return btime;
	}
	public void setBtime(String btime) {
		this.btime = btime;
	}
}
