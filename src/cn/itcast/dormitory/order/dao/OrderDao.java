package cn.itcast.dormitory.order.dao;

import java.sql.SQLException;
import java.util.List;

import cn.itcast.dormitory.order.entity.Order;
import cn.itcast.dormitory.pager.PageBean;

public interface OrderDao {
	public int findStatus(String oid) throws SQLException;// 查询订单状态

	public boolean updateStatus(String oid, int status) throws Exception;// 修改订单状态

	public Order load(String oid) throws SQLException;// 加载订单

	public void add(Order order) throws SQLException, Exception;// 生成订单

	public PageBean<Order> findByUser(String sid, int pc) throws SQLException;// 通过用户查找订单

	public PageBean<Order> findAll(int pc) throws SQLException;// 查询所有订单

	public PageBean<Order> findByWorker(String wid, int pc) throws SQLException;// 通过维修师傅查找订单

	public List<Order> findByStatus(int status) throws SQLException;// 通过订单状态查询

	public PageBean<Order> findByDorimtory(String dor, int pc) throws SQLException;

	public boolean addPl(String pl, String dj, String oid) throws SQLException;

	public PageBean<Order> findByAdmin(String address, int pc) throws SQLException;

}
