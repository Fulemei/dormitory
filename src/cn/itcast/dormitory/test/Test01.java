package cn.itcast.dormitory.test;

import java.util.ArrayList;
import java.util.List;

import cn.itcast.dormitory.user.dao.UserDao;
import cn.itcast.dormitory.user.dao.UserDaoImpl;
import cn.itcast.dormitory.user.entity.User;

public class Test01 {

	public static void main(String[] args) {
//		List<User> list = new ArrayList<User>();
//		UserDao ud = new UserDaoImpl();
//		list = ud.getUserAll();
//		System.out.println(list);
		String email = "804718591@qq.com";
		UserDao ud = new UserDaoImpl();
		boolean b = ud.ajaxValidateEmail(email);
		System.out.println(b);
	}

}
