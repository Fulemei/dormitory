package cn.itcast.dormitory.order.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.dormitory.order.entity.Order;
import cn.itcast.dormitory.order.service.OrderService;
import cn.itcast.dormitory.order.service.OrderServiceImpl;

/**
 * Servlet implementation class LoadOrder
 */
public class adminLoadOrder extends HttpServlet {
	private OrderService orderService = new OrderServiceImpl();
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public adminLoadOrder() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String oid = "";
		try {
			oid = req.getParameter("itemId");
		} catch (Exception e) {
		}

		if (!"".equals(oid) && null != oid) {
			Order order = orderService.load(oid);
			req.setAttribute("order", order);
		}
		req.getRequestDispatcher("itemDetail1.jsp").forward(req, resp);
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
