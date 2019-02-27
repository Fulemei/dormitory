package cn.itcast.dormitory.order.dao;

import java.sql.SQLException;
import java.util.List;

import cn.itcast.dormitory.order.entity.Order;

public interface OrderDao {
	public int findStatus(String oid) throws SQLException;//查询订单状态
	public void updateStatus(String oid, int status);//修改订单状态
	public Order load(String oid) throws SQLException;//加载订单
	public void add(Order order) throws SQLException;//生成订单
	public List<Order> findByUser(String sid) throws SQLException;//通过用户查找订单
	public List<Order> findAll() throws SQLException;//查询所有订单 
	public List<Order> findByWorker(String wid) throws SQLException;//通过维修师傅查找订单
	public List<Order> findByStatus(int status) throws SQLException;//通过订单状态查询
}
