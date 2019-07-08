package cn.itcast.dormitory.message;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class MessageServlet
 */
public class MessageServlet extends BaseServlet {
	MessageService ms = new MessageService();

	public void addMessage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PrintWriter out = resp.getWriter();
		Map<String, Object> res = new HashMap<String, Object>();
		Message m = CommonUtils.toBean(req.getParameterMap(), Message.class);
		String mid = CommonUtils.uuid();
		m.setMid(mid);
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String time = format.format(date);
		m.setCreate_date(time);

		boolean flag = ms.addMessage(m);
		if (flag) {
			res.put("state", true);
		} else {
			res.put("state", false);
			res.put("msg", "当前系统繁忙，请稍后再试");
		}
		JSONObject jsonObject = JSONObject.fromObject(res);
		System.out.println("返回" + jsonObject.toString());
		out.print(jsonObject.toString());
		out.flush();
		out.close();
	}

	public void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PrintWriter out = resp.getWriter();
		Map<String, Object> res = new HashMap<String, Object>();
		String mid = req.getParameter("mid");
		boolean flag = ms.delete(mid);
		if (flag) {
			res.put("state", true);
		} else {
			res.put("state", false);
			res.put("msg", "当前系统繁忙，请稍后再试");
		}
		JSONObject jsonObject = JSONObject.fromObject(res);
		System.out.println("返回" + jsonObject.toString());
		out.print(jsonObject.toString());
		out.flush();
		out.close();
	}
}
