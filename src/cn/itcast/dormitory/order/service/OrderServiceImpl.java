package cn.itcast.dormitory.order.service;

import java.sql.SQLException;
import java.util.List;

import cn.itcast.dormitory.order.dao.OrderDao;
import cn.itcast.dormitory.order.dao.OrderDaoImpl;
import cn.itcast.dormitory.order.entity.Order;
import cn.itcast.dormitory.pager.PageBean;

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
	public boolean updateStatus(String oid, int status) {
		boolean flag = false;
		try {
			flag = od.updateStatus(oid, status);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return flag;
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
	public PageBean<Order> findByUser(String sid, int pc) {
		try {
			return od.findByUser(sid, pc);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public PageBean<Order> findAll(int pc) {
		try {
			return od.findAll(pc);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public PageBean<Order> findByWorker(String wid, int pc) {
		try {
			return od.findByWorker(wid, pc);
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

	@Override
	public PageBean<Order> findByDorimtory(String dor, int pc) {
		try {
			return od.findByDorimtory(dor, pc);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean addPl(String pl, String dj, String oid) throws SQLException {
		return od.addPl(pl, dj, oid);
	}

	public PageBean<Order> findByAdmin(String address, int pc) throws SQLException {
		return od.findByAdmin(address, pc);
	}

}
