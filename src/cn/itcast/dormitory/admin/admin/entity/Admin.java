package cn.itcast.dormitory.admin.admin.entity;

public class Admin {
	private String adminid;//主键
	private String adminname;//管理员的登录名
	private String password;//管理员的登录密码
	public String getAdminid() {
		return adminid;
	}
	public void setAdminid(String adminid) {
		this.adminid = adminid;
	}
	public String getAdminname() {
		return adminname;
	}
	public void setAdminname(String adminname) {
		this.adminname = adminname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Admin [adminid=" + adminid + ", adminname=" + adminname + ", password=" + password + "]";
	}
	
}
