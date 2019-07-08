package cn.itcast.dormitory.admin.admin.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.commons.CommonUtils;
import cn.itcast.dormitory.admin.admin.entity.Admin;
import cn.itcast.dormitory.admin.admin.service.AdminService;
import cn.itcast.dormitory.pager.PageBean;
import cn.itcast.servlet.BaseServlet;
import net.sf.json.JSONObject;

public class AdminServlet extends BaseServlet {
	private AdminService adminService = new AdminService();

	/**
	 * 登录功能
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException
	 */
	public void login(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, SQLException {
		resp.addHeader("Access-Control-Allow-Origin", "*");
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		Map<String, Object> res = new HashMap<String, Object>();
		/*
		 * 1. 封装表单数据到Admin
		 */
		Admin form = CommonUtils.toBean(req.getParameterMap(), Admin.class);

		boolean flag = adminService.login(form);
		if (!flag) {
			res.put("state", false);
			res.put("msg", "用户名或密码错误！");
		} else {
			// 获取用户名保存到cookie中
			String adminid = form.getAdminid();
			PageBean<Admin> pb = adminService.findByAdminid(adminid, 1);
			Admin admin = pb.getBeanList().get(0);
			adminid = URLEncoder.encode(adminid, "utf-8");
			Cookie cookie = new Cookie("adminid", adminid);
			cookie.setMaxAge(60 * 60 * 24 * 10);// 保存10天
			resp.addCookie(cookie);
			// 保存用户到session
			req.getSession().setAttribute("sessionUser", admin);
			res.put("state", true);
		}
		JSONObject jsonObject = JSONObject.fromObject(res);
		System.out.println("返回" + jsonObject.toString());
		out.print(jsonObject.toString());
		out.flush();
		out.close();
	}

	public void updateAdmin(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, SQLException {
		PrintWriter out = resp.getWriter();
		Map<String, Object> res = new HashMap<String, Object>();
		Admin admin = CommonUtils.toBean(req.getParameterMap(), Admin.class);
		boolean flag = adminService.update(admin);
		if (flag) {
			res.put("state", true);
		} else {
			res.put("state", false);
			res.put("msg", "系统繁忙！请稍后再试~");
		}
		JSONObject jsonObject = JSONObject.fromObject(res);
		System.out.println("返回" + jsonObject.toString());
		out.print(jsonObject.toString());
		out.flush();
		out.close();
	}

	public void addAdmin(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, SQLException {
		PrintWriter out = resp.getWriter();
		Map<String, Object> res = new HashMap<String, Object>();
		Admin admin = CommonUtils.toBean(req.getParameterMap(), Admin.class);
		boolean flag = adminService.add(admin);
		if (flag) {
			res.put("state", true);
		} else {
			res.put("state", false);
			res.put("msg", "系统繁忙！请稍后再试~");
		}
		JSONObject jsonObject = JSONObject.fromObject(res);
		System.out.println("返回" + jsonObject.toString());
		out.print(jsonObject.toString());
		out.flush();
		out.close();
	}
}
