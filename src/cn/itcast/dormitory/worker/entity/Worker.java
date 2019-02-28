package cn.itcast.dormitory.worker.entity;

import cn.itcast.dormitory.category.entity.Category;

public class Worker {
	 private String wid;
     private String wname;
     private String wpassword;
     private Category category;
     private String wemail;
     private String wnumber;
	public String getWid() {
		return wid;
	}
	public void setWid(String wid) {
		this.wid = wid;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public String getWname() {
		return wname;
	}
	public void setWname(String wname) {
		this.wname = wname;
	}
	public String getWpassword() {
		return wpassword;
	}
	public void setWpassword(String wpassword) {
		this.wpassword = wpassword;
	}
	public String getWemail() {
		return wemail;
	}
	public void setWemail(String wemail) {
		this.wemail = wemail;
	}
	public String getWnumber() {
		return wnumber;
	}
	public void setWnumber(String wnumber) {
		this.wnumber = wnumber;
	}
	@Override
	public String toString() {
		return "Worker [wid=" + wid + ", wname=" + wname + ", wpassword=" + wpassword + ", category=" + category
				+ ", wemail=" + wemail + ", wnumber=" + wnumber + "]";
	}
     
}
