package cn.itcast.dormitory.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.itcast.dormitory.admin.admin.dao.AdminDao;
import cn.itcast.dormitory.category.dao.CategoryDao;
import cn.itcast.dormitory.category.dao.CategoryDaoImpl;
import cn.itcast.dormitory.category.entity.Category;
import cn.itcast.dormitory.category.service.CategoryService;
import cn.itcast.dormitory.category.service.CategoryServiceImpl;
import cn.itcast.dormitory.order.dao.OrderDao;
import cn.itcast.dormitory.order.dao.OrderDaoImpl;
import cn.itcast.dormitory.order.entity.Order;
import cn.itcast.dormitory.user.dao.UserDao;
import cn.itcast.dormitory.user.dao.UserDaoImpl;
import cn.itcast.dormitory.user.entity.User;
import cn.itcast.dormitory.worker.dao.WorkerDao;
import cn.itcast.dormitory.worker.dao.WorkerDaoImpl;
import cn.itcast.dormitory.worker.entity.Worker;
import sun.misc.UCDecoder;

public class Test01 {

	public static void main(String[] args) throws SQLException {

//        CategoryDao cd = new CategoryDaoImpl();
//		System.out.println(cd.findChildrenCountByParent("01"));
//        System.out.println(cd.findParents());
//        System.out.println(cd.findAll());
//        
//        CategoryService cs =new CategoryServiceImpl();
//        cs.findAll();
//        System.out.println(cs);
//      	WorkerDao wd = new WorkerDaoImpl();
//      	wd.findWorkerCountByCategory("01");
//      	System.out.println(wd.findWorkerCountByCategory("01"));
//		Worker w = new Worker();
//		w.setCid("01");
//		w.setWemail("111111111111@163.com");
//		w.setWnumber("1111111111111");
//		w.setWpassword("201801");
//		w.setWid("201801");
//		wd.add(w);
//		System.out.println(wd.findAll());
//		System.out.println(wd.findByWid("201801"));
		OrderDao oo = new OrderDaoImpl();
//		oo.findStatus("3267000BD379443D91138A449732CBC0");
////		System.out.println(oo.findStatus("3267000BD379443D91138A449732CBC0"));
//		oo.updateStatus("3267000BD379443D91138A449732CBC0", 2);
//		User user = new User();
//		Order order = new Order();
//		user.setSid("2015023301");
//		order.setOwner(user);
//		Category c = new Category();
//		c.setCid("04");
//		order.setCategory(c);
//		order.setAddress("05611");
//		order.setOrdermsg("洗衣机坏了");
//		oo.add(order);
//		System.out.println(oo.load("5783F20FDE1B48ACA28531A4E0758D7A"));
		AdminDao ad = new AdminDao();
		ad.login("123", "88888888");
		System.out.println(ad.login("123", "88888888"));
	}

}
