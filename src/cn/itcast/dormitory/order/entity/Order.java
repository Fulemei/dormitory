package cn.itcast.dormitory.order.entity;

import cn.itcast.dormitory.user.entity.User;
import cn.itcast.dormitory.worker.entity.Worker;

public class Order {
	private String oid;// 主键
	private String ordertime;// 下单时间
	private int status;// 订单状态：1未付款, 2已付款但未发货, 3已发货未确认收货, 4确认收货了交易成功, 5已取消(只有未付款才能取消)
	private String address;// 收货地址
	private User owner;// 订单的所有者
	private Worker worker;// 订单的派指人
	private String ordermsg;// 订单信息
	private String cid;// 订单所属分类
	private String descr;// 维修日志描述信息
	private String endtime;// 维修完成时间
	private String worktime;// 上门维修时间
	private String pl;
	private String dj;
	private int zt;

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getOrdertime() {
		return ordertime;
	}

	public void setOrdertime(String ordertime) {
		this.ordertime = ordertime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int i) {
		this.status = i;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public Worker getWorker() {
		return worker;
	}

	public void setWorker(Worker worker) {
		this.worker = worker;
	}

	public String getOrdermsg() {
		return ordermsg;
	}

	public void setOrdermsg(String ordermsg) {
		this.ordermsg = ordermsg;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getWorktime() {
		return worktime;
	}

	public void setWorktime(String worktime) {
		this.worktime = worktime;
	}

	public String getPl() {
		return pl;
	}

	public void setPl(String pl) {
		this.pl = pl;
	}

	public String getDj() {
		return dj;
	}

	public void setDj(String dj) {
		this.dj = dj;
	}

	public int getZt() {
		return zt;
	}

	public void setZt(int zt) {
		this.zt = zt;
	}

	@Override
	public String toString() {
		return "Order [oid=" + oid + ", ordertime=" + ordertime + ", status=" + status + ", address=" + address
				+ ", owner=" + owner + ", worker=" + worker + ", ordermsg=" + ordermsg + ", cid=" + cid + ", descr="
				+ descr + ", endtime=" + endtime + ", worktime=" + worktime + ", pl=" + pl + ", dj=" + dj + ", zt=" + zt
				+ "]";
	}

}
