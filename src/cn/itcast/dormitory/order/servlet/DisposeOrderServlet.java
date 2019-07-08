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
 * Servlet implementation class DisposeOrderServlet
 */
public class DisposeOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	OrderService orderService = new OrderServiceImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DisposeOrderServlet() {
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
		String oid = "";
		try {
			oid = req.getParameter("itemId");
		} catch (Exception e) {
		}
		/*
		 * 校验订单状态
		 */
		int status = orderService.findStatus(oid);
		if (status == 3 || status == 2) {
			res.put("state", false);
			res.put("msg", "该订单已完成！");
		} else {
			orderService.updateStatus(oid, 2);// 设置状态为交易成功！
			res.put("state", true);
			res.put("msg", "维修完成！");
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
