package cn.itcast.dormitory.message;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class DeleteMessage
 */
public class DeleteMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MessageService ms = new MessageService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteMessage() {
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
		String mid = "";
		try {
			mid = req.getParameter("mid");
		} catch (Exception e) {
		}

		if (!"".equals(mid) && null != mid) {
			boolean flag = ms.delete(mid);
			if (flag) {
				res.put("state", true);
			} else {
				res.put("state", false);
				res.put("msg", "删除失败");
			}
		} else {
			res.put("state", false);
			res.put("msg", "系统异常");
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
