package cn.itcast.dormitory.worker.entity;

public class Worker {
	private String wid;
	private String wname;
	private String wpassword;
	private int cid;
	private String wemail;
	private String wnumber;

	public String getWid() {
		return wid;
	}

	public void setWid(String wid) {
		this.wid = wid;
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

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

}
