package cn.itcast.dormitory.user.entity;

public class User {
	//对应数据库表
    private String sid;//主键
    private String name;//姓名
    private String password;//登录密码
    private String email;//邮箱
    private String dormitory;//寝室号
    private String tel_number;//手机号
    private String classes;//班级
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDormitory() {
		return dormitory;
	}
	public void setDormitory(String dormitory) {
		this.dormitory = dormitory;
	}
	public String getTel_number() {
		return tel_number;
	}
	public void setTel_number(String tel_number) {
		this.tel_number = tel_number;
	}
	public String getClasses() {
		return classes;
	}
	public void setClasses(String classes) {
		this.classes = classes;
	}
	@Override
	public String toString() {
		return "User [sid=" + sid + ", name=" + name + ", password=" + password + ", email=" + email + ", dormitory="
				+ dormitory + ", tel_number=" + tel_number + ", classes=" + classes + "]";
	}


}
