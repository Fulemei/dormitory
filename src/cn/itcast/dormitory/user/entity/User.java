package cn.itcast.dormitory.user.entity;

public class User {
	// 对应数据库表
	private String method;
	private String sid;// 主键
	private String name;// 姓名
	private String password;// 登录密码
	private String email;// 邮箱
	private String dormitory;// 寝室号
	private String tel_number;// 手机号
	private String classes;// 班级
	private String repass;
	private String verifyCode;
	private String newPass;
	private int sex;

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getNewPass() {
		return newPass;
	}

	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public String getRepass() {
		return repass;
	}

	public void setRepass(String repass) {
		this.repass = repass;
	}

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
				+ dormitory + ", tel_number=" + tel_number + ", classes=" + classes + ", repass=" + repass
				+ ", verifyCode=" + verifyCode + ", newPass=" + newPass + "]";
	}

}
