package cn.itcast.dormitory.message;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoadMessage
 */
public class LoadMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MessageService ms = new MessageService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoadMessage() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String mid = "";
		try {
			mid = req.getParameter("mid");
		} catch (Exception e) {
		}

		if (!"".equals(mid) && null != mid) {
			try {
				Message m = ms.findByMid(mid);
				req.setAttribute("message", m);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		req.getRequestDispatcher("MessageDetai.jsp").forward(req, resp);
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
