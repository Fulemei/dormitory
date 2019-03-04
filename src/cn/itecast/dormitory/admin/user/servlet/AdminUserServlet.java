package cn.itecast.dormitory.admin.user.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import cn.itcast.commons.CommonUtils;
import cn.itcast.dormitory.category.entity.Category;
import cn.itcast.dormitory.user.entity.User;
import cn.itcast.dormitory.user.service.UserService;
import cn.itcast.dormitory.user.service.UserServiceImpl;
import cn.itcast.dormitory.worker.entity.Worker;
import cn.itcast.servlet.BaseServlet;

public class AdminUserServlet extends BaseServlet{
	 private UserService us = new UserServiceImpl();
     /**
      * 删除学生
      */
	public String delete(HttpServletRequest req, HttpServletResponse resp) {
		String sid = req.getParameter("sid");
		us.delete(sid);
		req.setAttribute("msg", "删除学生成功");
		return "f:/adminjsps/msg.jsp";
	}
	/**
	 * 修改学生信息
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String edit(HttpServletRequest req, HttpServletResponse resp) {
		Map map = req.getParameterMap();
		User user = CommonUtils.toBean(map, User.class);		
		us.update(user);
		req.setAttribute("msg", "修改信息成功！");
		return "f:/adminjsps/msg.jsp";
	}
	/**
	 * 添加学生
	 */
	public String add(HttpServletRequest req, HttpServletResponse resp) {
		Map map = req.getParameterMap();
		User user = CommonUtils.toBean(map, User.class);
		us.register(user);
		req.setAttribute("msg", "添加成功");
		return "f:/adminjsps/msg.jsp";
	}
	/**
	 * 根据学号查找学生
	 * @throws SQLException 
	 */
	public String findBySid(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
		String sid = req.getParameter("sid");
		List<User> list= us.findBySid(sid);
		req.setAttribute("stu", list);
		return "f:/adminjsps/msg.jsp";
	}
	/**
	 * 查看所有学生信息
	 */
	public String findAll(HttpServletRequest req, HttpServletResponse resp) {
		List<User> list = us.getUserAll();
		req.setAttribute("stu", list);
		return "f:/adminjsps/msg.jsp";
	}
}
