package cn.itcast.dormitory.category.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import cn.itcast.dormitory.category.entity.Category;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.dormitory.category.service.CategoryService;
import cn.itcast.dormitory.category.service.CategoryServiceImpl;


public class CategoryServlet {
private CategoryService cs = new CategoryServiceImpl();	
	
	/**
	 * 查询所有分类
	 * @throws SQLException 
	 */
	public String findAll(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, SQLException {
		/*
		 * 1. 通过service得到所有的分类
		 * 2. 保存到request中，转发到left.jsp
		 */
		List<Category> parents = cs.findAll();
		req.setAttribute("parents", parents);
		return "f:/jsps/left.jsp";
	}
}
