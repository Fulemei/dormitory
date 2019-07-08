package cn.itcast.dormitory.order.service;

import java.sql.SQLException;
import java.util.List;

import cn.itcast.dormitory.order.entity.Order;
import cn.itcast.dormitory.pager.PageBean;

public interface OrderService {
	public int findStatus(String oid);// 查询订单状态

	public boolean updateStatus(String oid, int status);// 修改订单状态

	public Order load(String oid);// 加载订单

	public void add(Order order);// 生成订单

	public PageBean<Order> findAll(int pc);// 查询所有订单

	public PageBean<Order> findByWorker(String wid, int pc);// 通过维修师傅查找订单

	public List<Order> findByStatus(int status);// 通过订单状态查询

	public PageBean<Order> findByDorimtory(String dor, int pc);

	public PageBean<Order> findByUser(String sid, int pc);

	public boolean addPl(String pl, String dj, String oid) throws SQLException;

	public PageBean<Order> findByAdmin(String address, int pc) throws SQLException;
}
