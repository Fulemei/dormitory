package cn.itcast.dormitory.admin.category.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.commons.CommonUtils;
import cn.itcast.dormitory.category.entity.Category;
import cn.itcast.dormitory.category.service.CategoryService;
import cn.itcast.dormitory.category.service.CategoryServiceImpl;
import cn.itcast.dormitory.worker.service.WorkerService;
import cn.itcast.dormitory.worker.service.WorkerServiceImpl;

public class AdminCategoryServlet {
	private CategoryService cs = new CategoryServiceImpl();
	private WorkerService ws = new WorkerServiceImpl(); 
	
	/**
	 * 查询所有分类
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException 
	 */
	public String findAll(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, SQLException {
		req.setAttribute("parents", cs.findAll());
		return "f:/adminjsps/admin/category/list.jsp";
	}
	/**
	 * 添加一级分类
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException 
	 */
	public String addParent(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, SQLException {
		/*
		 * 1. 封装表单数据到Category中
		 * 2. 调用service的add()方法完成添加
		 * 3. 调用findAll()，返回list.jsp显示所有分类
		 */
		Category parent = CommonUtils.toBean(req.getParameterMap(), Category.class);
		parent.setCid(CommonUtils.uuid());//设置cid
		cs.add(parent);
		return findAll(req, resp);
	}
}
