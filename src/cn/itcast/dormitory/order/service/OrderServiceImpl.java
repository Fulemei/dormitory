package cn.itcast.dormitory.order.service;

import java.sql.SQLException;
import java.util.List;

import cn.itcast.dormitory.order.dao.OrderDao;
import cn.itcast.dormitory.order.dao.OrderDaoImpl;
import cn.itcast.dormitory.order.entity.Order;
    
public class OrderServiceImpl implements OrderService {
    OrderDao od = new OrderDaoImpl();

	@Override
	public int findStatus(String oid) {
		try {
			return od.findStatus(oid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void updateStatus(String oid, int status) {
		try {
			od.updateStatus(oid, status);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


	@Override
	public Order load(String oid) {
		try {
			return od.load(oid);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void add(Order order) {
		try {
			od.add(order);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Order> findByUser(String sid) {
		try {
			return od.findByUser(sid);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Order> findAll() {
		try {
			return od.findAll();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Order> findByWorker(String wid) {
		try {
			return od.findByWorker(wid);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Order> findByStatus(int status) {
		try {
			return od.findByStatus(status);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	
}
