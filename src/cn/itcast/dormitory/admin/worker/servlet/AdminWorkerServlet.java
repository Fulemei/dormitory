package cn.itcast.dormitory.admin.worker.servlet;

import cn.itcast.servlet.BaseServlet;

public class AdminWorkerServlet extends BaseServlet {
//	private WorkerService ws = new WorkerServiceImpl();
//	private CategoryService cs = new CategoryServiceImpl();
//
//	/**
//	 * 删除维修工
//	 */
//	public String delete(HttpServletRequest req, HttpServletResponse resp) {
//		String wid = req.getParameter("wid");
//		ws.delete(wid);
//		req.setAttribute("msg", "删除图书成功！");
//		return "f:/adminjsps/msg.jsp";
//	}
//
//	/**
//	 * 修改维修工信息
//	 * 
//	 * @param req
//	 * @param resp
//	 * @return
//	 * @throws ServletException
//	 * @throws IOException
//	 */
//	public String edit(HttpServletRequest req, HttpServletResponse resp) {
//		Map map = req.getParameterMap();
//		Worker worker = CommonUtils.toBean(map, Worker.class);
//		ws.edit(worker);
//		req.setAttribute("msg", "修改信息成功！");
//		return "f:/adminjsps/msg.jsp";
//	}
//
//	/**
//	 * 加载维修工
//	 * 
//	 * @param req
//	 * @param resp
//	 * @return
//	 * @throws SQLException
//	 * @throws ServletException
//	 * @throws IOException
//	 */
//	public String load(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
//		/*
//		 * 获取wid
//		 */
//		String wid = req.getParameter("wid");
//		Worker worker = new Worker();
//		List<Worker> list = ws.findByWid(wid);
//		worker = list.get(0);
//
//		/*
//		 * 4. 转发到desc.jsp显示
//		 */
//		return "f:/adminjsps/admin/book/desc.jsp";
//	}
//
//	/**
//	 * 添加工人
//	 * 
//	 * @param req
//	 * @param resp
//	 * @return
//	 * @throws ServletException
//	 * @throws IOException
//	 * @throws SQLException
//	 */
//	public String addWor(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
//		/*
//		 * 1. 获取所有一级分类，保存之 2. 转发到add.jsp，该页面会在下拉列表中显示所有一级分类
//		 */
//		List<Category> parents = cs.findParents();
//		req.setAttribute("parents", parents);
//		return "f:/adminjsps/admin/book/add.jsp";
//	}
//
//	public String addWorker(HttpServletRequest req, HttpServletResponse resp) {
//		Map map = req.getParameterMap();
//		Worker worker = CommonUtils.toBean(map, Worker.class);
//		ws.add(worker);
//		req.setAttribute("msg", "添加成功！");
//		return "f:/adminjsps/msg.jsp";
//	}
//
//	public String ajaxFindChildren(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
//		/*
//		 * 1. 获取pid 2. 通过pid查询出所有2级分类 3. 把List<Category>转换成json，输出给客户端
//		 */
//		String pid = req.getParameter("pid");
//		List<Category> children = cs.findByParent(pid);
//		String json = toJson(children);
//		resp.getWriter().print(json);
//		return null;
//	}
//
//	private String toJson(Category category) {
//		StringBuilder sb = new StringBuilder("{");
//		sb.append("\"cid\"").append(":").append("\"").append(category.getCid()).append("\"");
//		sb.append(",");
//		sb.append("\"cname\"").append(":").append("\"").append(category.getCname()).append("\"");
//		sb.append("}");
//		return sb.toString();
//	}
//
//	private String toJson(List<Category> categoryList) {
//		StringBuilder sb = new StringBuilder("[");
//		for (int i = 0; i < categoryList.size(); i++) {
//			sb.append(toJson(categoryList.get(i)));
//			if (i < categoryList.size() - 1) {
//				sb.append(",");
//			}
//		}
//		sb.append("]");
//		return sb.toString();
//	}
//
//	/**
//	 * 显示所有分类
//	 * 
//	 * @param req
//	 * @param resp
//	 * @return
//	 * @throws ServletException
//	 * @throws IOException
//	 * @throws SQLException
//	 */
//	public String findCategoryAll(HttpServletRequest req, HttpServletResponse resp)
//			throws ServletException, IOException, SQLException {
//		/*
//		 * 1. 通过service得到所有的分类 2. 保存到request中，转发到left.jsp
//		 */
//		List<Category> parents = cs.findAll();
//		req.setAttribute("parents", parents);
//		return "f:/adminjsps/admin/book/left.jsp";
//	}
//
//	/**
//	 * 按分类查找
//	 * 
//	 * @throws SQLException
//	 */
//	public String findByCategory(HttpServletRequest req, HttpServletResponse resp)
//			throws ServletException, IOException, SQLException {
//		String cid = req.getParameter("cid");
//		List<Worker> list = ws.findByCategory(cid);
//		req.setAttribute("pb", list);
//		return "f:/adminjsps/admin/book/list.jsp";
//	}
//
//	/**
//	 * 按工号查找
//	 * 
//	 * @throws SQLException
//	 */
//	public String findByWid(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
//		String wid = req.getParameter("wid");
//		List<Worker> list = ws.findByWid(wid);
//		req.setAttribute("pb", list);
//		return "f:/adminjsps/admin/book/list.jsp";
//	}
//
//	/**
//	 * 查看所有工人信息
//	 * 
//	 * @throws SQLException
//	 */
//	public String findAll(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
//		List<Worker> list = ws.findAll();
//		req.setAttribute("pb", list);
//		return "f:/adminjsps/admin/book/list.jsp";
//	}

}
