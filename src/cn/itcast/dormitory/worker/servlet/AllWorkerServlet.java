package cn.itcast.dormitory.worker.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.dormitory.pager.PageBean;
import cn.itcast.dormitory.worker.entity.Worker;
import cn.itcast.dormitory.worker.service.WorkerService;
import cn.itcast.dormitory.worker.service.WorkerServiceImpl;

/**
 * Servlet implementation class AllWorkerServlet
 */
public class AllWorkerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private WorkerService workerService = new WorkerServiceImpl();

	/**
	 * 获得当前页码
	 */
	public int getPc(HttpServletRequest req) {
		int pc = 1;
		String param = req.getParameter("pc");
		System.out.println("-----" + param);
		if (param != null) {
			try {
				pc = Integer.parseInt(param);
			} catch (RuntimeException e) {
			}
		} else {
			pc = 1;
		}
		System.out.println(pc);
		return pc;
	}

	/**
	 * 获得当前路径
	 */
	/*
	 * http://localhost:8080/goods/BookServlet?methed=findByCategory&cid=xxx&pc=3
	 * /goods/BookServlet + methed=findByCategory&cid=xxx&pc=3
	 */
	public String getUrl(HttpServletRequest req) {
		String url = req.getRequestURI() + "?" + req.getQueryString();
		int index = url.lastIndexOf("&pc=");
		if (index != -1) {
			url = url.substring(0, index);
		}
		return url;
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AllWorkerServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
		String wid = req.getParameter("wid");
		/*
		 * 4. 调用service
		 */
		try {
			PageBean<Worker> pb = workerService.findAll(pc);
			pb.setUrl(url);
			req.setAttribute("pb", pb);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * 5. 给PageBean设置url，保存PageBean，转发到/jsps/book/list.jsp
		 */
		req.getRequestDispatcher("WorkerList.jsp").forward(req, resp);
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
