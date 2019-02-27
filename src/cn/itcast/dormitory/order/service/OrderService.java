package cn.itcast.dormitory.order.service;

import java.sql.SQLException;
import java.util.List;

import cn.itcast.dormitory.order.entity.Order;

public interface OrderService {
	public int findStatus(String oid)  ;//查询订单状态
	public void updateStatus(String oid, int status);//修改订单状态
	public Order load(String oid) ;//加载订单
	public void add(Order order)  ;//生成订单
	public List<Order> findByUser(String sid)  ;//通过用户查找订单
	public List<Order> findAll() ;//查询所有订单 
	public List<Order> findByWorker(String wid) ;//通过维修师傅查找订单
	public List<Order> findByStatus(int status) ;//通过订单状态查询
}
