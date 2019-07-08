package cn.itcast.dormitory.worker.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.commons.CommonUtils;
import cn.itcast.dormitory.pager.PageBean;
import cn.itcast.dormitory.worker.entity.Worker;
import cn.itcast.dormitory.worker.service.WorkerService;
import cn.itcast.dormitory.worker.service.WorkerServiceImpl;
import cn.itcast.servlet.BaseServlet;
import net.sf.json.JSONObject;

public class WorkerServlet extends BaseServlet {
	private WorkerService workerService = new WorkerServiceImpl();

	/**
	 * 获得当前页码
	 */
	public int getPc(HttpServletRequest req) {
		int pc = 1;
		String param = req.getParameter("pc");
		if (param != null || param.trim().isEmpty()) {
			try {
				pc = Integer.parseInt(param);
			} catch (RuntimeException e) {

			}
		}
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
	 * 通过wid查询
	 * 
	 * @throws SQLException
	 * @throws IOException
	 * @throws ServletException
	 */
	public void findByWid(HttpServletRequest req, HttpServletResponse resp)
			throws SQLException, ServletException, IOException {
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
		PageBean<Worker> pb = workerService.findByWid(wid, pc);
		/*
		 * 5. 给PageBean设置url，保存PageBean，转发到/jsps/book/list.jsp
		 */
		pb.setUrl(url);

		req.setAttribute("pb", pb);

		req.getRequestDispatcher("repairMyList.jsp").forward(req, resp);
	}

	public void findAll(HttpServletRequest req, HttpServletResponse resp)
			throws SQLException, ServletException, IOException {
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
		PageBean<Worker> pb = workerService.findAll(pc);
		/*
		 * 5. 给PageBean设置url，保存PageBean，转发到/jsps/book/list.jsp
		 */
		pb.setUrl(url);

		req.setAttribute("pb", pb);

		req.getRequestDispatcher("WorkerList.jsp").forward(req, resp);
	}

	/**
	 * 通过分类查询
	 * 
	 * @throws SQLException
	 */
	public void findWorkerCountByCategory(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
		// 获得当前页码
		int pc = getPc(req);
		// 得到url
		String url = getUrl(req);
		// 获得参数
		String cid = req.getParameter("cid");
		// 使用pc 和 cid 调用WorkerService#findWorkerCountByCategory方法
		PageBean<Worker> list = workerService.findByCategory(cid, pc);

		req.setAttribute("pb", list);
		return;
	}

	public void addWorker(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
		PrintWriter out = resp.getWriter();
		Map<String, Object> res = new HashMap<String, Object>();

		Worker formWorker = CommonUtils.toBean(req.getParameterMap(), Worker.class);
		/*
		 * 1. 创建Worker
		 */
		Map<String, String> errors = validateRegist(formWorker);
		if (errors.size() > 0) {
			res.put("state", false);
			res.put("msg", errors);
			JSONObject jsonObject = JSONObject.fromObject(res);
			out.print(jsonObject.toString());
			out.flush();
			out.close();
		}
		/*
		 * 2. 调用service完成添加
		 */
		boolean flag = workerService.add(formWorker);
		/*
		 * 3. 保存订单，转发到ordersucc.jsp
		 */
		System.out.println("flag" + flag);
		/*
		 * 4. 开始判断
		 */
		if (!flag) {
			res.put("state", false);
			res.put("msg", "新增工人失败！");
		} else {
			res.put("state", true);
		}
		JSONObject jsonObject = JSONObject.fromObject(res);
		System.out.println("返回" + jsonObject.toString());
		out.print(jsonObject.toString());
		out.flush();
		out.close();
	}

	private Map<String, String> validateRegist(Worker formWorker) {
		Map<String, String> errors = new HashMap<String, String>();
		String wid = formWorker.getWid();
		if (wid == null) {
			errors.put("loginname", "用户名不能为空！");
		} else if (wid.length() < 3 || wid.length() > 20) {
			errors.put("loginname", "用户名长度必须在3~20之间！");
		} else if (!workerService.checkWid(wid)) {
			errors.put("loginname", "工号已被注册！");
		}
		String email = formWorker.getWemail();
		if (email == null) {
			errors.put("email", "Email不能为空！");
		} else if (!email.matches("^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\\.[a-zA-Z0-9_-]{2,3}){1,2})$")) {
			errors.put("email", "Email格式错误！");
		} else if (!workerService.ajaxValidateEmail(email)) {
			errors.put("email", "Email已被注册！");
		}
		return errors;
	}

	public void update(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
		PrintWriter out = resp.getWriter();
		Map<String, Object> res = new HashMap<String, Object>();

		Worker worker = CommonUtils.toBean(req.getParameterMap(), Worker.class);
		System.out.println(worker);
		boolean flag = workerService.edit(worker);
		if (!flag) {
			res.put("state", false);
			res.put("msg", "修改失败！");
		} else {
			res.put("state", true);
		}
		JSONObject jsonObject = JSONObject.fromObject(res);
		System.out.println("返回" + jsonObject.toString());
		out.print(jsonObject.toString());
		out.flush();
		out.close();

	}
}
