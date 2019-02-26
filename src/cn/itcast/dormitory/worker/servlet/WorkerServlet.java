package cn.itcast.dormitory.worker.servlet;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.dormitory.worker.entity.Worker;
import cn.itcast.dormitory.worker.service.WorkerService;
import cn.itcast.dormitory.worker.service.WorkerServiceImpl;



public class WorkerServlet {
	private WorkerService workerService = new WorkerServiceImpl();
//    /**
//            * 获得当前页码
//     */
//	public int getPc(HttpServletRequest req) {
//		int pc = 1;
//		String param = req.getParameter("pc");
//		if(param!=null||param.trim().isEmpty()) {
//			try {
//			pc = Integer.parseInt(param);
//			}catch(RuntimeException e) {
//				
//			}
//		}
//		return pc;
//	}
//	/**
//	 * 获得当前路径
//	 */
//	/*
//	 * http://localhost:8080/goods/BookServlet?methed=findByCategory&cid=xxx&pc=3
//	 * /goods/BookServlet + methed=findByCategory&cid=xxx&pc=3
//	 */
//	public String getUrl(HttpServletRequest req) {
//		String url = req.getRequestURI()+"?"+req.getQueryString();
//		int index = url.lastIndexOf("&pc=");
//		if(index!=-1) {
//		url = url.substring(0, index);
//		}
//		return url;
//	}
	/**
	 * 通过wid查询
	 * @throws SQLException 
	 */
	public String  findByWid(HttpServletRequest req,HttpServletResponse resp) throws SQLException {
		String wid = req.getParameter("wid");
		Worker worker = workerService.findByWid(wid).get(0);
		req.setAttribute("worker", worker);
		return "f:/jsps/worker/list.jsp";
	}
	/**
	 * 通过分类查询
	 * @throws SQLException 
	 */
	public String findWorkerCountByCategory(HttpServletRequest req,HttpServletResponse resp) throws SQLException {
//		//获得当前页码
//		int pc = getPc(req);
//		//得到url
//		String url = getUrl(req);
//		//获得参数
		String cid = req.getParameter("cid");
		//使用pc 和 cid 调用WorkerService#findWorkerCountByCategory方法
		List<Worker> list = workerService.findByCategory(cid);
		
		req.setAttribute("pb", list);
		return "f:/jsps/worker/list.jsp";
	}
}
