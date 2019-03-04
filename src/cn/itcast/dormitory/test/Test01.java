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
//
//		CategoryDao cd = new CategoryDaoImpl();
//        System.out.println(cd.load("051"));		
		
	OrderDao od = new OrderDaoImpl();
	
	//System.out.println(od.findByDorimtory("05611"));
	od.updateStatus("5783F20FDE1B48ACA28531A4E0758D7A", 2);
	//System.out.println(od.findByWorker("201801"));
	}

}
