package cn.itcast.dormitory.order.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.dormitory.order.service.OrderService;
import cn.itcast.dormitory.order.service.OrderServiceImpl;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class OrderCancel
 */
public class OrderCancel extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderService orderService = new OrderServiceImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OrderCancel() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		Map<String, Object> res = new HashMap<String, Object>();
		String oid = req.getParameter("oid");
		/*
		 * 校验订单状态
		 */

		int status = orderService.findStatus(oid);
		if (status == 1 || status == 2) {
			res.put("state", false);
			res.put("msg", "该订单已完成维修");
		} else {
			boolean flag = orderService.updateStatus(oid, 3);// 设置状态为取消！
			System.out.println("*****" + flag);
			if (flag) {
				res.put("state", true);
				res.put("msg", "取消成功");
			} else {
				res.put("state", false);
				res.put("msg", "取消异常，请稍后重试");
			}
		}
		JSONObject jsonObject = JSONObject.fromObject(res);
		out.print(jsonObject.toString());
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
