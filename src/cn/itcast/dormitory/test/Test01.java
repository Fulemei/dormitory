package cn.itcast.dormitory.test;

import java.sql.SQLException;

import cn.itcast.dormitory.order.dao.OrderDao;
import cn.itcast.dormitory.order.dao.OrderDaoImpl;
import cn.itcast.dormitory.order.entity.Order;
import cn.itcast.dormitory.pager.PageBean;
import cn.itcast.dormitory.worker.dao.WorkerDao;
import cn.itcast.dormitory.worker.dao.WorkerDaoImpl;

public class Test01 {

	public static void main(String[] args) throws SQLException {
		Order o = new Order();
		OrderDao od = new OrderDaoImpl();
		// PageBean<Order> pb = od.findAll(1);
		PageBean<Order> pb = od.findByUser("2015023301", 1);
		System.out.println(pb);

//		boolean flag = false;
//		OrderDao od = new OrderDaoImpl();
//		Order o = new Order();
//		User u = new User();
//		o.setAddress("八舍611");
//		o.setCid("01");
//		o.setOrdermsg("插排没电");
//		u.setName("付乐梅");
//		u.setTel_number("13314903613");
//		o.setOwner(u);
//		od.add(o);

//		Category c = new Category();
//		Worker worker = new Worker();
		WorkerDao wd = new WorkerDaoImpl();
//		worker.setCid("01");
//		worker.setWid("201809");
//		worker.setWemail("15245121430@163.com");
//		worker.setWname("陈立农");
//		worker.setWpassword("201809");
//		wd.add(worker);
//		List<Worker> list = wd.findAll();
//		int i = wd.findWorkerCountByCategory("01");
//		System.out.println("i" + i);
//		System.out.println("所有的维修人员" + list);

//		OrderDao od = new OrderDaoImpl();
//		int i = od.findStatus("5783F20FDE1B48ACA28531A4E0758D7A");
//		System.out.println("订单状态" + i);
//
//		od.updateStatus("DF1D7EDF5AF34CC0B8E786229758FC1E", 2);
//		List<Order> list = od.findAll();
//		System.out.println(list);

//		User user = new User();
//		UserDao ud = new UserDaoImpl();
////		System.out.println("更改密码成功?" + flag);
////		flag = ud.updatePassword("2015023217", "2015023302", "2015023304");
////		System.out.println("更改密码成功?" + flag);
//		flag = ud.ajaxValidateSid("2015023217");
//		System.out.println("是否已经注册" + flag);
//		List<User> list = ud.findBySid("2015023217");
//		System.out.println("根据学号查用户信息" + list);

//		String sid = "2015023217";
//		String password = "2015023217";
//		flag = ud.login(sid, password);
//		System.out.println("登录成功?" + flag);
//		List<User> list = ud.getUserAll();
//		System.out.println("所有用户信息" + list);
//		user.setSid("2015023217");
//		user.setName("王嘉铭");
//		user.setClasses("电信15-4");
//		user.setDormitory("五舍611");
//		ud.update(user);
//		list = ud.findBySid("2015023217");
//		System.out.println("根据学号查用户信息" + list);

//		flag = ud.register(user);
//		System.out.println("注册成功?" + flag);

//
//		CategoryDao cd = new CategoryDaoImpl();
//        System.out.println(cd.load("051"));		
//		
//	OrderDao od = new OrderDaoImpl();
//	
//	//System.out.println(od.findByDorimtory("05611"));
//	od.updateStatus("5783F20FDE1B48ACA28531A4E0758D7A", 2);
//	//System.out.println(od.findByWorker("201801"));
	}

}
