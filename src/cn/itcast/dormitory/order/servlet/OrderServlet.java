package cn.itcast.dormitory.order.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.commons.CommonUtils;
import cn.itcast.dormitory.admin.admin.entity.Admin;
import cn.itcast.dormitory.order.entity.Order;
import cn.itcast.dormitory.order.service.OrderService;
import cn.itcast.dormitory.order.service.OrderServiceImpl;
import cn.itcast.dormitory.pager.PageBean;
import cn.itcast.dormitory.user.entity.User;
import cn.itcast.servlet.BaseServlet;
import net.sf.json.JSONObject;

public class OrderServlet extends BaseServlet {
	private OrderService orderService = new OrderServiceImpl();

	/**
	 * 获取当前页码
	 * 
	 * @param req
	 * @return
	 */
	private int getPc(HttpServletRequest req) {
		int pc = 1;
		String param = req.getParameter("pc");
		if (param != null && !param.trim().isEmpty()) {
			try {
				pc = Integer.parseInt(param);
			} catch (RuntimeException e) {
			}
		}
		return pc;
	}

	/**
	 * 截取url，页面中的分页导航中需要使用它做为超链接的目标！
	 * 
	 * @param req
	 * @return
	 */
	/*
	 * http://localhost:8080/goods/BookServlet?methed=findByCategory&cid=xxx&pc=3
	 * /goods/BookServlet + methed=findByCategory&cid=xxx&pc=3
	 */
	private String getUrl(HttpServletRequest req) {
		String url = req.getRequestURI() + "?" + req.getQueryString();
		/*
		 * 如果url中存在pc参数，截取掉，如果不存在那就不用截取。
		 */
		int index = url.lastIndexOf("&pc=");
		if (index != -1) {
			url = url.substring(0, index);
		}
		return url;
	}

	/**
	 * 支付准备
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String paymentPre(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("order", orderService.load(req.getParameter("oid")));
		return "f:/jsps/order/pay.jsp";
	}

	/**
	 * 取消订单
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public void cancel(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		Map<String, Object> res = new HashMap<String, Object>();
		String oid = req.getParameter("oid");
		/*
		 * 校验订单状态
		 */
		boolean flag = orderService.updateStatus(oid, 3);// 设置状态为取消！
		int status = orderService.findStatus(oid);
		if (status == 1 || status == 2) {
			res.put("state", false);
			res.put("msg", "该订单已完成维修");
		} else if (flag) {
			res.put("state", true);
			res.put("msg", "取消成功");
		} else {
			res.put("state", false);
			res.put("msg", "取消异常，请稍后重试");
		}
		JSONObject jsonObject = JSONObject.fromObject(res);
		out.print(jsonObject.toString());
		out.flush();
		out.close();
	}

	/**
	 * 维修登记
	 */
	public String Dengji(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String oid = req.getParameter("oid");
		/*
		 * 校验订单状态
		 */
		int status = orderService.findStatus(oid);
		if (status != 0) {
			req.setAttribute("code", "error");
			req.setAttribute("msg", "该订单已取消或已完成！");
			return "f:/jsps/msg.jsp";
		}
		orderService.updateStatus(oid, 2);
		req.setAttribute("code", "success");
		req.setAttribute("msg", "工作人员已上门维修！");
		return "f:/jsps/msg.jsp";
	}

	/**
	 * 维修成功
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String confirm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String oid = req.getParameter("oid");
		/*
		 * 校验订单状态
		 */
		int status = orderService.findStatus(oid);

		orderService.updateStatus(oid, 3);// 设置状态为交易成功！
		req.setAttribute("code", "success");
		req.setAttribute("msg", "维修完成！");
		return "f:/jsps/msg.jsp";
	}

	/**
	 * 加载订单
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String load(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String oid = req.getParameter("oid");
		Order order = orderService.load(oid);
		req.setAttribute("order", order);
		String btn = req.getParameter("btn");// btn说明了用户点击哪个超链接来访问本方法的
		req.setAttribute("btn", btn);
		return "/jsps/order/desc.jsp";
	}

	public void findByWid(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*
		 * 1. 得到pc：如果页面传递，使用页面的，如果没传，pc=1
		 */
		int pc = getPc(req);
		/*
		 * 2. 得到url：...
		 */
		String url = getUrl(req);
		/*
		 * 4. 调用service
		 */
		String wid = req.getParameter("wid");
		PageBean<Order> pb = orderService.findByWorker(wid, pc);
		/*
		 * 5. 给PageBean设置url，保存PageBean，转发到/jsps/book/list.jsp
		 */
		pb.setUrl(url);
		req.setAttribute("pb", pb);
		req.getRequestDispatcher("repairAllList.jsp").forward(req, resp);
	}

	/**
	 * 生成订单
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.addHeader("Access-Control-Allow-Origin", "*");
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		Map<String, Object> res = new HashMap<String, Object>();
		String oid = CommonUtils.uuid();
		System.out.println("订单编码" + oid);
		/*
		 * 1. 创建Order
		 */
		Order order = new Order();
		System.out.println(req.getParameter("cid"));
		order.setAddress(req.getParameter("address"));// 设置收货地址
		order.setOrdermsg(req.getParameter("ordermsg"));
		order.setCid(req.getParameter("cid"));
		order.setOid(oid);
		User owner = (User) req.getSession().getAttribute("sessionUser");
		System.out.println("user" + owner);
		User user = new User();
		user.setSid(owner.getSid());
		user.setName(req.getParameter("name"));
		user.setTel_number(req.getParameter("tel_number"));
		order.setOwner(user);// 设置订单所有者

		/*
		 * 2. 调用service完成添加
		 */
		orderService.add(order);
		/*
		 * 3. 保存订单，转发到ordersucc.jsp
		 */
		res.put("state", true);
		JSONObject jsonObject = JSONObject.fromObject(res);
		System.out.println("返回" + jsonObject.toString());
		out.print(jsonObject.toString());
		out.flush();
		out.close();
	}

	/**
	 * 生成订单
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public void admincreateOrder(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.addHeader("Access-Control-Allow-Origin", "*");
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		Map<String, Object> res = new HashMap<String, Object>();
		/*
		 * 1. 创建Order
		 */
		Order order = new Order();
		System.out.println(req.getParameter("cid"));
		order.setAddress(req.getParameter("address"));// 设置收货地址
		order.setOrdermsg(req.getParameter("ordermsg"));
		order.setCid(req.getParameter("cid"));
		Admin admin = (Admin) req.getSession().getAttribute("sessionUser");
		System.out.println("user" + admin);
		User user = new User();
		user.setSid(admin.getAdminid());
		user.setName(req.getParameter("name"));
		user.setTel_number(req.getParameter("tel_number"));
		order.setOwner(user);// 设置订单所有者

		/*
		 * 2. 调用service完成添加
		 */
		orderService.add(order);
		/*
		 * 3. 保存订单，转发到ordersucc.jsp
		 */
		res.put("state", true);
		JSONObject jsonObject = JSONObject.fromObject(res);
		System.out.println("返回" + jsonObject.toString());
		out.print(jsonObject.toString());
		out.flush();
		out.close();
	}

	/**
	 * 我的订单
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public void myOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("进入myOrders");
		/*
		 * 1. 得到pc：如果页面传递，使用页面的，如果没传，pc=1
		 */
		int pc = getPc(req);
		/*
		 * 2. 得到url：...
		 */
		String url = getUrl(req);
		/*
		 * 3. 从当前session中获取User
		 */
		User user = (User) req.getSession().getAttribute("sessionUser");
		/*
		 * 4. 调用service
		 */
		PageBean<Order> pb = orderService.findByUser(user.getSid(), pc);
		/*
		 * 5. 给PageBean设置url，保存PageBean，转发到/jsps/book/list.jsp
		 */
		pb.setUrl(url);
		req.setAttribute("pb", pb);
		req.getRequestDispatcher("repairMyList.jsp").forward(req, resp);
	}

	public void pingjia(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, SQLException {
		PrintWriter out = resp.getWriter();
		Map<String, Object> res = new HashMap<String, Object>();
		String pl = req.getParameter("pl");
		String dj = req.getParameter("dj");
		String oid = (String) req.getSession().getAttribute("oid");
		boolean flag = orderService.addPl(pl, dj, oid);
		if (flag) {
			res.put("state", true);
		} else {
			res.put("state", false);
			res.put("msg", "提交失败，请稍后再试");
		}
		JSONObject jsonObject = JSONObject.fromObject(res);
		System.out.println("返回" + jsonObject.toString());
		out.print(jsonObject.toString());
		out.flush();
		out.close();
	}
}
