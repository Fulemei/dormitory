package cn.itcast.dormitory.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.itcast.dormitory.category.dao.CategoryDao;
import cn.itcast.dormitory.category.dao.CategoryDaoImpl;
import cn.itcast.dormitory.category.entity.Category;
import cn.itcast.dormitory.user.dao.UserDao;
import cn.itcast.dormitory.user.dao.UserDaoImpl;
import cn.itcast.dormitory.user.entity.User;
import sun.misc.UCDecoder;

public class Test01 {

	public static void main(String[] args) throws SQLException {
////		List<User> list = new ArrayList<User>();
////		UserDao ud = new UserDaoImpl();
////		list = ud.getUserAll();
////		System.out.println(list);
//		String email = "804718591@qq.com";
//		UserDao ud = new UserDaoImpl();
//		boolean b = ud.ajaxValidateEmail(email);
//		System.out.println(b);
//		
		CategoryDao cd = new CategoryDaoImpl();
		System.out.println(cd.findChildrenCountByParent("01"));
//        System.out.println(cd.findParents());
//        System.out.println(cd.findAll());
//        //System.out.println(cd.load("011"));
//        
//        Category category = new Category();
//        Category parent = new Category();
//        parent.setCid("01");
//        category.setCid("013");
//        category.setCname("厕所");
//        category.setParent(parent);
//        category.setDesc("    ");
//        cd.add(category);
        System.out.println(cd.findParents());
        System.out.println(cd.findByParent("09"));
       cd.delete("013");
	}

}
